package br.com.intuitiv.templatespring.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

  public static boolean validar(final String pattern, String string) {
    Pattern pat;
    Matcher mat;

    try {
      string = string.replaceAll("\r", "").replaceAll("\n", "");
      pat = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
      mat = pat.matcher(string);

      return mat.find();
    } finally {
      pat = null;
      mat = null;
    }
  }

  public static String getStringPatterner(final String pattern, String texto) {
    Pattern pat;
    Matcher mat;

    try {
      texto = texto.replaceAll("\r", "").replaceAll("\n", "");
      pat = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
      mat = pat.matcher(texto);

      if (mat.find()) {
        return texto.substring(mat.start(), mat.end());
      } else {
        return null;
      }
    } finally {
      pat = null;
      mat = null;
    }
  }

  public static List<String> getListStringPatterner(final String pattern, String texto) {
    final Pattern pat;
    final Matcher mat;
    int posicao = 0;
    final List<String> ret = new ArrayList<>();
    texto = texto.replaceAll("\r", "").replaceAll("\n", "");
    pat = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    mat = pat.matcher(texto);

    while (mat.find(posicao)) {
      ret.add(texto.substring(mat.start(), mat.end()));
      posicao = mat.end();
    }

    return ret.size() > 0 ? ret : null;
  }
}
