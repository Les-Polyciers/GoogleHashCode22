package exam_solution;

import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class Pizza {

  private final Set<String> ingredients;

  public String createOutput() {
    return ingredients.size() + " " + String.join(" ", ingredients);
  }

  public static Pizza fromClient(Client client) {
    return new Pizza(client.getLikedIngredients());
  }
}
