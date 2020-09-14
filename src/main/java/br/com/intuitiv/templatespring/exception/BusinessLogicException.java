package br.com.intuitiv.templatespring.exception;

public class BusinessLogicException extends RuntimeException {

  private static final String BUSINESS_LOGIC = "error.business-logic";
  private static final long serialVersionUID = 5809467594479735001L;

  public BusinessLogicException() {
    super(BUSINESS_LOGIC);
  }

  public BusinessLogicException(final String message) {
    super(message);
  }
}
