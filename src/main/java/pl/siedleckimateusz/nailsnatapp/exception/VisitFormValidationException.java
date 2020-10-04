package pl.siedleckimateusz.nailsnatapp.exception;

public class VisitFormValidationException extends Throwable {

    public VisitFormValidationException(VisitErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
