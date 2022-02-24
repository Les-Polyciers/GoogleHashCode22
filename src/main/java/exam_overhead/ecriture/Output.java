package exam_overhead.ecriture;

import exam_overhead.main.DataSetFileName;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;

@UtilityClass
public final class Output {

  private static final String OUTPUT_DATA_FOLDER_NAME = "outputData";

  public static void write(String output, DataSetFileName dataSetFileName)
      throws URISyntaxException, IOException {

    URI uri = Output.class.getProtectionDomain().getCodeSource().getLocation().toURI();

    File targetFolder = new File(uri).getParentFile();
    File outputFolder = new File(targetFolder, OUTPUT_DATA_FOLDER_NAME);

    if (!outputFolder.isDirectory()) {
      Files.createDirectory(outputFolder.toPath());
    }

    File outputFile = new File(outputFolder, dataSetFileName.createOutputFilename());

    Files.deleteIfExists(outputFile.toPath());

    Files.createFile(outputFile.toPath());

    try (PrintWriter printWriter = new PrintWriter(outputFile)) {
      printWriter.write(output);
    }
  }
}
