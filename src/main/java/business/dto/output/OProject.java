package business.dto.output;

import exam_overhead.main.NewLine;

import java.util.List;
import java.util.stream.Collectors;

public class OProject {
  private final String name;
  private final List<OContributor> assignments;

  public OProject(String name, List<OContributor> assignments) {
    this.name = name;
    this.assignments = assignments;
  }

  public String createOutput() {
    String contributors =
        assignments.stream().map(OContributor::createOutput).collect(Collectors.joining(" "));
    return name + NewLine.LF + contributors + NewLine.LF;
  }
}
