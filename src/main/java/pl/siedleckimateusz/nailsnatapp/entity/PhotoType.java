package pl.siedleckimateusz.nailsnatapp.entity;

public enum PhotoType {
    PATTERN("Wzornik"), REALIZATION("Wykonanie");

    private String name;

    PhotoType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
