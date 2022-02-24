package business.timeline;

import business.dto.Contributor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TContributor {
  private final Contributor contributor;
  private boolean isAvailable;

  public TContributor(Contributor contributor) {
    this.contributor = contributor;
    this.isAvailable = true;
  }
}
