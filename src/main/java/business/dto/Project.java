package business.dto;

import lombok.Getter;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

@Getter
public class Project {

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
}
