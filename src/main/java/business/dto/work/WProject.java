package business.dto.work;

import business.dto.Contributor;
import business.dto.Project;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WProject {

  private final Project project;
  private List<Contributor> assignations = new ArrayList<>();
  private int start;

  public WProject(Project p) {
    project = p;
  }
}
