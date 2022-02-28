package business.utils;

import business.dto.Contributor;
import business.dto.Project;
import business.dto.Skill;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Nullable;

@UtilityClass
public class ProjectHelper {
  public static double computeProjectRatio(Project project) {
    return ((double) project.getScoreForCompletion()) / project.getDuration();
  }

  public static boolean hasSkill(
      Skill skill, Contributor contributor, @Nullable Contributor bestMentor) {
    boolean canBeMentored =
        bestMentor != null && bestMentor.getSkillsMap().get(skill.getName()) >= skill.getLevel();
    int threshold = canBeMentored ? skill.getLevel() - 1 : skill.getLevel();
    return contributor.getSkillsMap().getOrDefault(skill.getName(), 0) >= threshold;
  }

  public static boolean isWorthDoing(Project project, int time) {
    //    return time < project.getEnd();
    return project.getBestBefore() >= time + project.getDuration();
  }
}
