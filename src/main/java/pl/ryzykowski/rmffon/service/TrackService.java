package pl.ryzykowski.rmffon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ryzykowski.rmffon.client.StationClient;
import pl.ryzykowski.rmffon.client.TrackClient;
import pl.ryzykowski.rmffon.dto.TrackLiteDTO;
import pl.ryzykowski.rmffon.model.Station;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackService {

    private TrackClient trackClient;
    private StationClient stationClient;

    @Autowired
    public TrackService(TrackClient trackClient, StationClient stationClient) {
        this.trackClient = trackClient;
        this.stationClient = stationClient;
    }

    public List<TrackLiteDTO> getTracks(String stationId){
        return trackClient.getTracks(stationId)
                .stream()
                .map(item -> new TrackLiteDTO(item.getStart(), item.getAuthor(), item.getTitle(), item.getLenght(), stationId, item.getVotes(), item.getPoints()))
                .collect(Collectors.toList());
    }

    public List<TrackLiteDTO> getAllTracks(){
        List<TrackLiteDTO> tracks = new ArrayList<>();
        for (Station station : stationClient.getStations()) {
            tracks.addAll(this.getTracks(station.getId()));
        }
        return tracks;
    }


}
