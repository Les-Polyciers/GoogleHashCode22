package business;

import business.dto.Contributor;
import business.dto.Project;
import lombok.Getter;

import java.io.InputStream;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class ProblemInputParameters {

  private final Set<Contributor> contributors;
  private final Set<Project> projects;

  public ProblemInputParameters(InputStream input) {

    try (Scanner scanner = new Scanner(input)) {
      int nbContributors = Integer.parseInt(scanner.next());
      int nbProject = Integer.parseInt(scanner.next());

      this.contributors =
          Stream.generate(() -> new Contributor(scanner))
              .limit(nbContributors)
              .collect(Collectors.toUnmodifiableSet());

      this.projects =
          Stream.generate(() -> new Project(scanner))
              .limit(nbProject)
              .collect(Collectors.toUnmodifiableSet());
    }
  }
}
