package business.dto.output;

public class OContributor {
  private final String name;

  public OContributor(String name) {
    this.name = name;
  }

  public String createOutput() {
    return this.name;
  }
}
