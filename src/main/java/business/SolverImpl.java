package business;

import business.dto.output.OContributor;
import business.dto.output.OProject;

import java.util.List;

public class SolverImpl implements Solver {

  @Override
  public ProblemOutputParameters solve(ProblemInputParameters inputParameters) {
    OContributor bob = new OContributor("Bob");
    OContributor ana = new OContributor("Anna");
    OContributor maria = new OContributor("Maria");
    OProject webserver = new OProject("WebServer", List.of(bob, ana));
    OProject logging = new OProject("Logging", List.of(ana));
    OProject webchat = new OProject("WebChat", List.of(maria, bob));
    return new ProblemOutputParameters(List.of(webserver, logging, webchat));
  }
}
