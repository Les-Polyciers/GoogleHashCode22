package exam_overhead.main;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class NewLine {

  private static final String CR = "\r";
  private static final String LF = "\n";

  private static final String CRLF = CR + LF;

  public static final String ANY_NEW_LINE = CR + "|" + LF + "|" + CRLF;
}
