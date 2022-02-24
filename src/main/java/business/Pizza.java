package business;

import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class Pizza {

  private final Set<String> ingredients;

  public String createOutput() {
    return ingredients.size() + " " + String.join(" ", ingredients);
  }
}
