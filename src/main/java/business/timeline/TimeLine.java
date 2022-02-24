package business.timeline;

import business.ProblemInputParameters;
import business.ProblemOutputParameters;
import business.Solver;
import business.dto.output.OContributor;
import business.dto.output.OProject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TimeLine implements Solver {

  List<TProject> waitingProjects = new ArrayList<>();
  List<TProject> runningProjects = new ArrayList<>();
  List<TContributor> contributors = new ArrayList<>();

  List<OProject> projectList = new ArrayList<>();

  boolean canDoProject = true;

  @Override
  public ProblemOutputParameters solve(ProblemInputParameters inputParameters) {
    canDoProject = true;
    projectList = new ArrayList<>();
    runningProjects = new ArrayList<>();
    this.waitingProjects = inputParameters.getProjects().stream().map(TProject::new).toList();
    this.contributors = inputParameters.getContributors().stream().map(TContributor::new).toList();
    while (canDoProject) {
      step();
    }
    return new ProblemOutputParameters(projectList);
  }

  private void step() {
    // Assign projects if possible
    List<TProject> toRemove = new ArrayList<>();
    for (TProject waitingProject : waitingProjects) {
      waitingProject.assignProjects(
          contributors.stream().filter(TContributor::isAvailable).collect(Collectors.toSet()));
      if (waitingProject.getContributors().size()
          == waitingProject.getProject().getRoles().size()) {
        toRemove.add(waitingProject);
        runningProjects.add(waitingProject);
        this.projectList.add(
            new OProject(
                waitingProject.getProject().getName(),
                waitingProject.getContributors().stream()
                    .map(contributor -> new OContributor(contributor.getContributor().getName()))
                    .toList()));
      }
    }

    for (TProject tProject : toRemove) {
      waitingProjects.remove(tProject);
    }

    if (runningProjects.isEmpty()) {
      this.canDoProject = false;
      return;
    }

    // Step running projects
    for (TProject runningProject : runningProjects) {
      boolean isFinished = runningProject.stepAndReturnIfFinished();
      if (isFinished) {
        this.runningProjects.remove(runningProject);
      }
    }
  }
}
