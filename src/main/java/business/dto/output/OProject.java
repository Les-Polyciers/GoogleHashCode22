package business.dto.output;

import exam_overhead.main.NewLine;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class OProject {

  private final String name;
  private final List<OContributor> assignments;

  public String createOutput() {
    String contributors =
        assignments.stream().map(OContributor::createOutput).collect(Collectors.joining(" "));
    return name + NewLine.LF + contributors + NewLine.LF;
  }
}
