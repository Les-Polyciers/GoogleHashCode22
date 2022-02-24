package business;

import business.dto.output.OProject;
import exam_overhead.main.NewLine;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ProblemOutputParameters {

  private final List<OProject> projects;

  public String createOutput() {
    return projects.size()
        + NewLine.LF
        + projects.stream().map(OProject::createOutput).collect(Collectors.joining());
  }
}
