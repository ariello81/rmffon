package pl.ryzykowski.rmffon.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.ryzykowski.rmffon.model.Station;

import java.util.List;

@Component
public class StationClient {

    private final RestTemplate restTemplate;

    private final String ENDPOINT_STATIONS = "http://www.rmfon.pl/json/stations.txt";

    @Autowired
    public StationClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Station> getStations(){
        ResponseEntity<List<Station>> responseEntity = restTemplate.exchange(ENDPOINT_STATIONS, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Station>>(){});
        return responseEntity.getBody();
    }


}
