package business.dto;

import lombok.Getter;

import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class Contributor {

  private final String name;
  private final Set<Skill> skills;

  public Contributor(Scanner scannerBeginsContributor) {
    this.name = scannerBeginsContributor.next();

    int nbSkills = Integer.parseInt(scannerBeginsContributor.next());
    this.skills =
        Stream.generate(() -> new Skill(scannerBeginsContributor))
            .limit(nbSkills)
            .collect(Collectors.toUnmodifiableSet());
  }
}
