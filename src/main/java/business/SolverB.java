package business;

import business.dto.Contributor;
import business.dto.Project;
import business.dto.Skill;
import business.dto.output.OContributor;
import business.dto.output.OProject;
import business.dto.work.Assignation;
import business.dto.work.WProject;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static business.utils.ProjectHelper.hasSkill;
import static business.utils.ProjectHelper.isWorthDoing;

public class SolverB implements Solver {
  private List<Contributor> freeContributors;
  private Map<Integer, List<Contributor>> busyContributors;
  private List<WProject> results = new ArrayList<>();

  @Override
  public ProblemOutputParameters solve(ProblemInputParameters inputParameters) {
    freeContributors = new ArrayList<>();
    busyContributors = new HashMap<>();
    results = new ArrayList<>();
    AtomicInteger timer = new AtomicInteger(0);
    // tri des plus urgents
    List<Project> projects =
        inputParameters.getProjects().stream()
            .sorted(projectComparators())
            .collect(Collectors.toList());
    freeContributors = new ArrayList<>(inputParameters.getContributors());

    int end = projects.stream().max(Comparator.comparing(Project::getEnd)).orElseThrow().getEnd();
    System.out.println("end = " + end);
    System.out.print("[");

    while (timer.get() <= end) {
      int t = timer.get();
      if (t == 0.1 * end
          || t == 0.2 * end
          || t == 0.3 * end
          || t == 0.4 * end
          || t == 0.5 * end
          || t == 0.6 * end
          || t == 0.7 * end
          || t == 0.8 * end
          || t == 0.9 * end
          || t == end) {
        System.out.print("=");
      }

      if (busyContributors.containsKey(t)) {
        freeContributors.addAll(busyContributors.get(t));
        busyContributors.remove(t);
      }
      // si on peut pas on passe sinon on place les gens
      List<Project> assignedProjects = new ArrayList<>();
      projects.forEach(
          p -> {
            int currentTime = timer.get();
            if (isWorthDoing(p, currentTime)) {
              WProject wp = new WProject(p);
              boolean ok = false;
              for (Skill r : p.getRoles()) {
                for (Contributor contributor :
                    findBestContributors(r, freeContributors, wp.getAssignations())) {
                  if (wp.getAssignations().stream()
                      .noneMatch(a -> a.getContributor().equals(contributor))) {
                    wp.getAssignations().add(new Assignation(contributor, r));
                    ok = true;
                    break;
                  }
                }
                if (!ok) {
                  break;
                }
              }
              if (wp.getAssignations().size() == p.getRoles().size()) {
                results.add(wp);
                wp.setStart(currentTime);
                freeContributors.removeAll(
                    wp.getAssignations().stream().map(Assignation::getContributor).toList());
                for (int i = 0; i < wp.getAssignations().size(); i++) {
                  Skill role = p.getRoles().get(i);
                  wp.getAssignations().get(i).getContributor().levelUp(role);
                }
                busyContributors.put(
                    currentTime + p.getDuration(),
                    wp.getAssignations().stream().map(Assignation::getContributor).toList());
                assignedProjects.add(p);
              }
            }
          });
      projects.removeAll(assignedProjects);
      timer.incrementAndGet();
    }
    System.out.print("]");

    return new ProblemOutputParameters(
        results.stream()
            .map(
                project ->
                    new OProject(
                        project.getProject().getName(),
                        project.getAssignations().stream()
                            .map(a -> new OContributor(a.getContributor().getName()))
                            .toList()))
            .toList());
  }

  private Comparator<Project> projectComparators() {
    return Comparator.comparing(Project::getTeamSize)
        .thenComparing(Project::getComplexity)
        .thenComparing(Project::getRatio);
  }

  static List<Contributor> findBestContributors(
      Skill skill, List<Contributor> freeContributors, List<Assignation> assignations) {
    Contributor bestMentor =
        assignations.stream()
            .map(Assignation::getContributor)
            .filter(contributor -> contributor.getSkillsMap().containsKey(skill.getName()))
            .max(skillComparator(skill))
            .orElse(null);
    return freeContributors.stream()
        .filter(
            c -> c.getSkillsMap().containsKey(skill.getName()) && hasSkill(skill, c, bestMentor))
        .sorted(skillComparator(skill))
        .toList();
  }

  static Comparator<Contributor> skillComparator(Skill skill) {
    return Comparator.comparingInt(
        a -> a.getSkillsMap().size() * a.getSkillsMap().get(skill.getName()));
  }

  private int compare(Contributor o1, Contributor o2) {
    return 0;
  }
}
