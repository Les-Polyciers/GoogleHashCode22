package business.utils;

import business.dto.Contributor;
import business.dto.Project;
import business.dto.Skill;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProjectHelper {
  public static double computeProjectRatio(Project project) {
    return ((double) project.getScoreForCompletion()) / project.getDuration();
  }

  public static boolean hasSkill(Skill skill, Contributor contributor) {
    return contributor.getSkills().stream()
        .filter(s -> s.getName().equals(skill.getName()))
        .anyMatch(s -> s.getLevel() >= skill.getLevel());
  }

  public static boolean isWorthDoing(Project project, int time) {
    return project.getBestBefore() >= time + project.getDuration();
  }
}
