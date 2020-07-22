package pl.ryzykowski.rmffon.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.ryzykowski.rmffon.dto.TrackLiteDTO;
import pl.ryzykowski.rmffon.model.Station;
import pl.ryzykowski.rmffon.model.Track;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
public class TrackClient {

    private final RestTemplate restTemplate;
    private final StationClient stationClient;

    private final String ENDPOINT_TRACKS = "http://rmfon.pl/stacje/playlista_X.json.txt";

    @Autowired
    public TrackClient(RestTemplate restTemplate, StationClient stationClient) {
        this.restTemplate = restTemplate;
        this.stationClient = stationClient;
    }

    public List<Track> getTracks(String stationId){
        ResponseEntity<List<Track>> responseEntity = restTemplate.exchange(ENDPOINT_TRACKS.replace("X", stationId),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Track>>(){});
        return responseEntity.getBody();
    }

    public List<Track> getAllTracks(){
        List<Track> tracks = new ArrayList<>();
        for (Station station : stationClient.getStations()) {
            tracks.addAll(getTracks(station.getId()));
        }
        return tracks;
    }

}
