package business.dto;

import java.util.Scanner;

public class Skill {

  private final String name;
  private final int level;

  public Skill(Scanner scannerBeginsSkill) {
    this.name = scannerBeginsSkill.next();
    this.level = Integer.parseInt(scannerBeginsSkill.next());
  }
}
