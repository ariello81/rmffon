package pl.ryzykowski.rmffon.dto;

import java.util.Objects;

public class StationDTO {

    private String id;
    private String name;
    private String radioService;

    public StationDTO() {
    }

    public StationDTO(String id, String name, String radioService) {
        this.id = id;
        this.name = name;
        this.radioService = radioService;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRadioService() {
        return radioService;
    }

    public void setRadioService(String radioService) {
        this.radioService = radioService;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationDTO that = (StationDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(radioService, that.radioService);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, radioService);
    }
}
