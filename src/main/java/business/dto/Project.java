package business.dto;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

@Getter
public class Project implements Comparable<Project> {

  private final String name;
  private final int duration;
  private final int scoreForCompletion;
  private final int bestBefore;
  private final List<Skill> roles;

  public Project(Scanner scannerBeginsProject) {
    this.name = scannerBeginsProject.next();
    this.duration = Integer.parseInt(scannerBeginsProject.next());
    this.scoreForCompletion = Integer.parseInt(scannerBeginsProject.next());
    this.bestBefore = Integer.parseInt(scannerBeginsProject.next());

    int nbRoles = Integer.parseInt(scannerBeginsProject.next());
    this.roles = Stream.generate(() -> new Skill(scannerBeginsProject)).limit(nbRoles).toList();
  }

  public double getRatio() {
    return ((double) scoreForCompletion) / (duration);
  }

  public int getTeamSize() {
    return roles.size();
  }

  public double getComplexity() {
    var teamSize = getTeamSize();
    return teamSize * roles.stream().mapToInt(Skill::getLevel).average().orElseThrow();
  }

  public int getEnd() {
    return Math.max(0, bestBefore + scoreForCompletion - duration);
  }

  @Override
  public int compareTo(@NotNull Project o) {
    if (bestBefore == o.bestBefore) {
      return 0;
    } else if (bestBefore > o.bestBefore) {
      return 1;
    } else {
      return -1;
    }
  }
}
