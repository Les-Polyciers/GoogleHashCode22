package business;

import business.dto.Contributor;
import business.dto.Project;
import exam_overhead.main.NewLine;
import lombok.Getter;

import java.io.InputStream;
import java.util.Scanner;
import java.util.Set;

@Getter
public class ProblemInputParameters {

  private final Set<Contributor> contributors;
  private final Set<Project> projects;

  public ProblemInputParameters(InputStream input) {

    try (Scanner scanner = new Scanner(input).useDelimiter(NewLine.ANY_NEW_LINE)) {
      // TODO
    }
  }
}
