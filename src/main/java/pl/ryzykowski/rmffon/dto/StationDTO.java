package pl.ryzykowski.rmffon.dto;

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
}
