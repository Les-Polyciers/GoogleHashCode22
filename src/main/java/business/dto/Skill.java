package business.dto;

import lombok.Getter;

import java.util.Scanner;

@Getter
public class Skill {

  private final String name;
  private final int level;

  public Skill(Scanner scannerBeginsSkill) {
    this.name = scannerBeginsSkill.next();
    this.level = Integer.parseInt(scannerBeginsSkill.next());
  }

  public boolean canBeHandledBy(Contributor contributor) {
    return contributor.getSkills().stream().anyMatch(this::canBeRoleOf);
  }

  private boolean canBeRoleOf(Skill skill) {
    return skill.getName().equals(name) && skill.getLevel() >= level;
  }
}
