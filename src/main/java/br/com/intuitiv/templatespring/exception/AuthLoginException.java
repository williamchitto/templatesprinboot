package br.com.intuitiv.templatespring.exception;

public class AuthLoginException extends RuntimeException {

  private static final String NOT_FOUND = "error.auth-login";
  private static final long serialVersionUID = 1880484219998783546L;

  public AuthLoginException() {
    super(NOT_FOUND);
  }

  public AuthLoginException(final String message) {
    super(message);
  }
}
