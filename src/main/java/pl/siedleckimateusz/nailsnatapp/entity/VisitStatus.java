package pl.siedleckimateusz.nailsnatapp.entity;

import lombok.Getter;

@Getter
public enum VisitStatus {
    TO_CONFIRM_BY_EMPLOYEE("NIEPOTWIERDZONA"),
    TO_CONFIRM_BY_USER("DO_POTWIERDZENIA"),
    CONFIRMED("POTWIERDZONA"),
    CANCELED("ANULOWANA");

    private final String statForEmployee;

    VisitStatus(String statForEmployee) {
        this.statForEmployee = statForEmployee;
    }


}
