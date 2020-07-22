package pl.ryzykowski.rmffon.dto;

public class StationDTO {

    private String id;
    private String name;
    private String tracksUrl;

    public StationDTO() {
    }

    public StationDTO(String id, String name, String tracksUrl) {
        this.id = id;
        this.name = name;
        this.tracksUrl = tracksUrl;
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

    public String getTracksUrl() {
        return tracksUrl;
    }

    public void setTracksUrl(String tracksUrl) {
        this.tracksUrl = tracksUrl;
    }
}
