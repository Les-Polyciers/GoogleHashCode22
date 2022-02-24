package exam_overhead.main;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class NewLine {

  public static final String CR = "\r";
  public static final String LF = "\n";

  public static final String CRLF = CR + LF;

  public static final String ANY_NEW_LINE = CRLF + "|" + CR + "|" + LF;
}
