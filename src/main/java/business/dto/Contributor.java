package business.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class Contributor {

  private final String name;
  private Map<String, Integer> skillsMap;

  public Contributor(String name) {
    this.name = name;
    this.skillsMap = new HashMap<>();
  }

  public Contributor(Scanner scannerBeginsContributor) {
    this.name = scannerBeginsContributor.next();

    int nbSkills = Integer.parseInt(scannerBeginsContributor.next());
    this.skillsMap =
        Stream.generate(() -> new Skill(scannerBeginsContributor))
            .limit(nbSkills)
            .collect(Collectors.toMap(Skill::getName, Skill::getLevel));
  }

  public boolean levelUp(Skill assignement) {
    int currentLevel = skillsMap.computeIfAbsent(assignement.getName(), s -> 0);
    boolean improveLevel =
        skillsMap.computeIfAbsent(assignement.getName(), s -> 0) <= assignement.getLevel();
    if (improveLevel) {
      skillsMap.put(assignement.getName(), ++currentLevel);
    }
    return improveLevel;
  }
}
