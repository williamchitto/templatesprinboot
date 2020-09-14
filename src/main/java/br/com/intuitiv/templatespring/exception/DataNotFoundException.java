package br.com.intuitiv.templatespring.exception;

public class DataNotFoundException extends RuntimeException {

  private static final String NOT_FOUND = "error.not-found";
  private static final long serialVersionUID = -6257314328828355704L;

  public DataNotFoundException() {
    super(NOT_FOUND);
  }

  public DataNotFoundException(final String message) {
    super(message);
  }
}
