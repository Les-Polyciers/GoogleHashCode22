package exam_overhead.main;

import business.ProblemInputParameters;
import business.ProblemOutputParameters;
import business.Solver;
import exam_overhead.ecriture.Output;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

@RequiredArgsConstructor
public final class FileManager {

  private final Solver solver;

  public void computeAllInputFiles() throws URISyntaxException, IOException {

    for (File inputFile : getAllInputFiles()) {
      if (inputFile.getName().startsWith("f_")) {
        computeFile(inputFile);
      }
    }
  }

  private File[] getAllInputFiles() throws URISyntaxException {

    @Nullable URL inputDataLocation = Main.class.getClassLoader().getResource("inputData");
    URI inputDataFolder = Objects.requireNonNull(inputDataLocation).toURI();
    @Nullable File[] inputFiles = new File(inputDataFolder).listFiles();
    return Objects.requireNonNull(inputFiles);
  }

  private void computeFile(File file) throws IOException, URISyntaxException {
    InputStream input = new FileInputStream(file);

    ProblemInputParameters inputParameters = new ProblemInputParameters(input);
    ProblemOutputParameters outputParameters = solver.solve(inputParameters);

    DataSetFileName dataSetFileName = new DataSetFileName(file.getName());

    Output.write(outputParameters.createOutput(), dataSetFileName);

    System.out.printf("Le fichier %s a été traité.%n", dataSetFileName.createInputFileName());
  }
}
