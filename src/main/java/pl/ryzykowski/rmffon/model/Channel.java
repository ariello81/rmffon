package pl.ryzykowski.rmffon.model;

import java.util.List;

public class Channel {

    private String id;

    private List<OpenFmTrack> tracks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<OpenFmTrack> getTracks() {
        return tracks;
    }

    public void setTracks(List<OpenFmTrack> tracks) {
        this.tracks = tracks;
    }
}
