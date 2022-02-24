package business;

import business.dto.Contributor;
import business.dto.Project;
import business.dto.output.OContributor;
import business.dto.output.OProject;
import business.dto.work.WProject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static business.utils.ProjectHelper.hasSkill;
import static business.utils.ProjectHelper.isWorthDoing;

public class SolverA implements Solver {
  private List<Contributor> freeContributors;
  //  private Map<String, Contributor> contributorMap;
  private Map<String, Integer> busyContributors;
  private List<WProject> results = new ArrayList<>();
  private int time;

  @Override
  public ProblemOutputParameters solve(ProblemInputParameters inputParameters) {
    freeContributors = new ArrayList<>();
    results = new ArrayList<>();
    time = 0;
    // tri des plus urgents
    List<Project> projects =
        inputParameters.getProjects().stream()
            .sorted(Comparator.comparing(Project::getBestBefore))
            .collect(Collectors.toList());
    freeContributors = new ArrayList<>(inputParameters.getContributors());

    // si on peut pas on passe sinon on place les gens
    projects.forEach(
        p -> {
          if (isWorthDoing(p, time)) {
            WProject wp = new WProject(p);
            p.getRoles()
                .forEach(
                    r -> {
                      for (Contributor contributor : freeContributors) {
                        if (hasSkill(r, contributor)) {
                          wp.getAssignations().add(contributor);
                        }
                      }
                    });
            if (wp.getAssignations().size() == p.getRoles().size()) {
              results.add(wp);
              wp.setStart(time);
              freeContributors.removeAll(wp.getAssignations());
              time += p.getDuration();
            }
          }
        });

    return new ProblemOutputParameters(
        results.stream()
            .map(
                project ->
                    new OProject(
                        project.getProject().getName(),
                        project.getAssignations().stream()
                            .map(a -> new OContributor(a.getName()))
                            .collect(Collectors.toList())))
            .collect(Collectors.toList()));
  }
}