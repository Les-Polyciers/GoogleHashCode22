package business;

import lombok.Getter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class Client {

  private final Set<String> likedIngredients;
  private final Set<String> dislikedIngredients;

  public Client(String inputLikes, String inputDislikes) {

    this.likedIngredients =
        Arrays.stream(inputLikes.split(" ")).skip(1).collect(Collectors.toUnmodifiableSet());

    this.dislikedIngredients =
        Arrays.stream(inputDislikes.split(" ")).skip(1).collect(Collectors.toUnmodifiableSet());
  }
}
