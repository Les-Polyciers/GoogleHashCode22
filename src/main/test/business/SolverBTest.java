package business;

import business.dto.Contributor;
import business.dto.Skill;
import business.dto.work.Assignation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class SolverBTest {
  private static List<Contributor> people;
  private static Contributor john, paul, stagiaire, alex;

  @BeforeAll
  static void initData() {
    john = new Contributor("john");
    john.setSkillsMap(Map.of("C", 3, "Java", 10, "HTML", 2, "Typescript", 1));
    paul = new Contributor("paul");
    paul.setSkillsMap(Map.of("C", 6, "Java", 2, "HTML", 3, "Typescript", 3));
    stagiaire = new Contributor("stagiaire");
    stagiaire.setSkillsMap(Map.of("C", 1, "Java", 1));
    alex = new Contributor("alex");
    alex.setSkillsMap(Map.of("C", 0, "Python", 7, "Java", 2, "HTML", 6, "Typescript", 5));

    people = List.of(john, paul, stagiaire, alex);

    Skill java3bis = new Skill("Java", 3);
    Skill ts = new Skill("Typescript", 2);
    Skill python = new Skill("Python", 1);
  }

  @Test
  void testSkillComparator() {
    Skill cBeginner = new Skill("C", 2);
    Skill java3 = new Skill("Java", 3);
    var sortedByCLevel =
        people.stream().sorted(SolverB.skillComparator(cBeginner)).collect(Collectors.toList());
    assertThat(sortedByCLevel).containsExactly(alex, stagiaire, john, paul);
    assertThat(people.stream().sorted(SolverB.skillComparator(java3)).collect(Collectors.toList()))
        .containsExactly(stagiaire, paul, alex, john);
  }

  @Test
  void testFindBest() {
    Skill cBeginner = new Skill("C", 2);
    Skill java3 = new Skill("Java", 3);
    assertThat(SolverB.findBestContributors(cBeginner, people, new ArrayList<>()).get(0))
        .isEqualTo(john);
    assertThat(
            SolverB.findBestContributors(
                    cBeginner, people, List.of(new Assignation(john, new Skill("C", 3))))
                .get(0))
        .isEqualTo(stagiaire);
    assertThat(SolverB.findBestContributors(java3, people, new ArrayList<>())).hasSize(1);
  }
}
