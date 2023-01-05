package pl.ryzykowski.rmffon.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.ryzykowski.rmffon.model.Station;
import pl.ryzykowski.rmffon.model.Track;

import java.util.List;

@Component
public class RmfClient {

    private final RestTemplate restTemplate;

    private final String ENDPOINT_STATIONS = "http://www.rmfon.pl/json/stations.txt";

    private final String ENDPOINT_TRACKS = "http://rmfon.pl/stacje/playlista_X.json.txt";

    @Autowired
    public RmfClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Station> getStations(){
        ResponseEntity<List<Station>> responseEntity = restTemplate.exchange(ENDPOINT_STATIONS, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Station>>(){});
        return responseEntity.getBody();
    }

    public List<Track> getTracks(String stationId){
        ResponseEntity<List<Track>> responseEntity = restTemplate.exchange(ENDPOINT_TRACKS.replace("X", stationId),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Track>>(){});
        return responseEntity.getBody();
    }


}
