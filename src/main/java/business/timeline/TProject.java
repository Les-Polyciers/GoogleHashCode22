package business.timeline;

import business.dto.Project;
import business.dto.Skill;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
public class TProject {
  private final Project project;
  private int timeRemaining;
  private List<TContributor> contributors;

  public TProject(Project project) {
    this.project = project;
    this.contributors = new ArrayList<>();
    this.timeRemaining = project.getScoreForCompletion();
  }

  public void assignProjects(Set<TContributor> availableContributors) {
    for (Skill role : this.project.getRoles()) {
      Optional<TContributor> foundContributor =
          availableContributors.stream()
              .filter(contributor -> role.canBeHandledBy(contributor.getContributor()))
              .findAny();
      if (foundContributor.isEmpty()) {
        this.contributors.forEach(contributor -> contributor.setAvailable(true));
        this.contributors = new ArrayList<>();
        return;
      }
      TContributor con = foundContributor.get();
      this.contributors.add(con);
      con.setAvailable(false);
    }
  }

  public boolean stepAndReturnIfFinished() {
    timeRemaining--;
    if (timeRemaining == 0) {
      contributors.forEach(contributor -> contributor.setAvailable(true));
      return true;
    }
    return false;
  }
}
