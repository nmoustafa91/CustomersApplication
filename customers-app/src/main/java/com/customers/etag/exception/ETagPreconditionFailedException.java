package com.customers.etag.exception;

import com.customers.exception.ValidationException;
import com.customers.exception.general.ApplicationError;

public class ETagPreconditionFailedException extends ValidationException {
  public ETagPreconditionFailedException(ApplicationError error) {
    super(error);
  }
}
