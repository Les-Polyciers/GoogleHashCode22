package business;

import lombok.Getter;

import java.util.Set;

@Getter
public class Client {

  private final Set<String> likedIngredients;
  private final Set<String> dislikedIngredients;

  public Client(String inputLikes, String inputDislikes) {
    this.likedIngredients = Set.of(inputLikes.split(" "));
    this.dislikedIngredients = Set.of(inputDislikes.split(" "));
  }
}
