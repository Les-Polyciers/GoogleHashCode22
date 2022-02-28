package business.dto;

import lombok.Getter;

import java.util.Map;
import java.util.Scanner;

@Getter
public class Skill {

  private final String name;
  private final int level;

  public Skill(String name, int level) {
    this.name = name;
    this.level = level;
  }

  public Skill(Scanner scannerBeginsSkill) {
    this.name = scannerBeginsSkill.next();
    this.level = Integer.parseInt(scannerBeginsSkill.next());
  }

  public boolean canBeHandledBy(Contributor contributor) {
    return contributor.getSkillsMap().entrySet().stream().anyMatch(this::canBeRoleOf);
  }

  private boolean canBeRoleOf(Map.Entry<String, Integer> skill) {
    return skill.getKey().equals(name) && skill.getValue() >= level;
  }
}
