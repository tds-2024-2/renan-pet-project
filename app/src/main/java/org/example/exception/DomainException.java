package org.example.exception;

public class DomainException extends RuntimeException {

  public DomainException(String msg) {
    super(msg);
  }
}