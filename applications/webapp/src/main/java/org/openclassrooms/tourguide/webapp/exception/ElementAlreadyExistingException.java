package org.openclassrooms.tourguide.webapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ElementAlreadyExistingException extends RuntimeException {

    /**
     * Exception to throw when try to save a already existing element.
     * @param message .
     */
    public ElementAlreadyExistingException(final String message) {
        super(message);
    }
}