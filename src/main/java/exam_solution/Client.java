package exam_solution;

import lombok.Getter;

import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class Client {

  private final Set<String> likedIngredients;
  private final Set<String> dislikedIngredients;

  public Client(String inputLikes, String inputDislikes) {

    try (Scanner scanner = new Scanner(inputLikes)) {
      scanner.next();
      this.likedIngredients = scanner.tokens().collect(Collectors.toUnmodifiableSet());
    }

    try (Scanner scanner = new Scanner(inputDislikes)) {
      scanner.next();
      this.dislikedIngredients = scanner.tokens().collect(Collectors.toUnmodifiableSet());
    }
  }
}
