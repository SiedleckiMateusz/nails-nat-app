package pl.siedleckimateusz.nailsnatapp.exception;

public enum VisitErrorMessage {

    EMPTY_TREATMENT("Nie wybrano zabiegu! Spróbuj ponownie"),
    TREATMENT_NOT_EXIST("Przykro nam, nie ma tekiego zabiegu.. Wybierz inny"),
    EMPTY_DATE("Nie wybrano daty a jest nam potrzebna. Proszę wybierz odpowiedni dzień"),
    NOT_AVAILABLE_DATE("Kurcze, wybrana data jest już nie dostępna. Proszę, wybierz inną"),
    NOT_AVAILABLE_DATE_AND_TIME("Przykro mi ale w międzyczasie wybrany termin wizyty został zarezerwowany. Proszę wybierz inny"),
    EMPTY_TIME("Nie wybrano godziny! Spróbuj ponownie"),

    ;


    private String message;

    VisitErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
