package business.best_score_first;

import business.ProblemInputParameters;
import business.ProblemOutputParameters;
import business.Solver;
import business.dto.Contributor;
import business.dto.Project;
import business.dto.Skill;
import business.dto.output.OContributor;
import business.dto.output.OProject;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class BestScoreFirst implements Solver {

  private int timeSpent = 0;

  @Override
  public ProblemOutputParameters solve(ProblemInputParameters inputParameters) {

    List<Project> bestScores =
        inputParameters.getProjects().stream()
            .sorted(Comparator.comparing(Project::getScoreForCompletion).reversed())
            .toList();

    List<OProject> oProjects =
        bestScores.stream()
            .filter(this::givesScore)
            .map(project -> computeAssignments(project, inputParameters.getContributors()))
            .filter(Objects::nonNull)
            .toList();

    return new ProblemOutputParameters(oProjects);
  }

  private boolean givesScore(Project project) {

    int late = timeSpent + project.getDuration() - project.getBestBefore();

    boolean givesScore = late < project.getScoreForCompletion();

    if (givesScore) {
      timeSpent += project.getDuration();
    }

    return givesScore;
  }

  private @Nullable OProject computeAssignments(Project project, Set<Contributor> contributors) {
    @Nullable List<Contributor> contributorsAssigned = computeContributors(project, contributors);

    if (contributorsAssigned == null) {
      return null;
    }

    return new OProject(
        project.getName(),
        contributorsAssigned.stream()
            .map(contributor -> new OContributor(contributor.getName()))
            .toList());
  }

  private @Nullable List<Contributor> computeContributors(
      Project project, Set<Contributor> contributors) {

    Set<Contributor> availableContributors = new HashSet<>(contributors);
    List<Contributor> workingContributors = new ArrayList<>(contributors.size());

    for (Skill role : project.getRoles()) {
      Optional<Contributor> foundContributor =
          availableContributors.stream().filter(role::canBeHandledBy).findAny();
      if (foundContributor.isEmpty()) {
        return null;
      }
      Contributor con = foundContributor.get();
      workingContributors.add(con);
      availableContributors.remove(con);
    }

    return workingContributors;
  }
}
