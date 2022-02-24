package business.dto.output;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OContributor {
  private final String name;

  public String createOutput() {
    return this.name;
  }
}
