package exam_overhead.main;

public class DataSetFileName {

  private static final String INPUT_DESCRIPTOR = "in";
  private static final String OUTPUT_DESCRIPTOR = "out";

  private final String mainName;
  private final String extension;

  public DataSetFileName(String fileName) {

    int extensionPointIndex = fileName.lastIndexOf(".");
    int typeDescriptionPointIndex = fileName.lastIndexOf(".", extensionPointIndex - 1);

    this.mainName = fileName.substring(0, typeDescriptionPointIndex);
    this.extension = fileName.substring(extensionPointIndex + 1);
  }

  public String createInputFileName() {
    return mainName + "." + INPUT_DESCRIPTOR + "." + extension;
  }

  public String createOutputFilename() {
    return mainName + "." + OUTPUT_DESCRIPTOR + "." + extension;
  }
}
