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

    private List<Station> stations;

    @Autowired
    public TrackService(TrackClient trackClient, StationClient stationClient) {
        this.trackClient = trackClient;
        this.stationClient = stationClient;
        this.stations = stationClient.getStations();
    }

    public List<TrackLiteDTO> getTracks(String stationId){
        return trackClient.getTracks(stationId)
                .stream()
                .map(item -> new TrackLiteDTO(item.getStart(), item.getAuthor(), item.getTitle(), item.getLenght(),
                        stations.stream().filter(station -> station.getId().equals(stationId)).findFirst().get().getName(),
                        item.getVotes(), item.getPoints()))
                .collect(Collectors.toList());
    }

    public List<TrackLiteDTO> getAuthorTracks(String author){
        int i=0;
        List<TrackLiteDTO> tracks = new ArrayList<>();
        for (Station station : stations) {
            tracks.addAll(this.getTracks(station.getId()));
            System.out.println(i++);
        }
        return tracks.stream().filter(item -> item.getAuthor().equalsIgnoreCase(author)).collect(Collectors.toList());
    }


}
