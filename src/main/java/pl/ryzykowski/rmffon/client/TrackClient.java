package pl.ryzykowski.rmffon.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.ryzykowski.rmffon.model.Track;

import java.util.List;

@Component
public class TrackClient {

    private final RestTemplate restTemplate;

    private final String ENDPOINT_TRACKS = "http://rmfon.pl/stacje/playlista_X.json.txt";

    @Autowired
    public TrackClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Track> getTracks(String stationId){
        ResponseEntity<List<Track>> responseEntity = restTemplate.exchange(ENDPOINT_TRACKS.replace("X", stationId),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Track>>(){});
        return responseEntity.getBody();
    }

}
