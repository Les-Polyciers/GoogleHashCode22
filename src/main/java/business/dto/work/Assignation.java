package business.dto.work;

import business.dto.Contributor;
import business.dto.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Assignation {
  private Contributor contributor;
  private Skill retainedSkill;
}
