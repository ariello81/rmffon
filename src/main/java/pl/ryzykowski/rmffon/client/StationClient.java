package pl.ryzykowski.rmffon.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.ryzykowski.rmffon.model.OpenFm;
import pl.ryzykowski.rmffon.model.Station;

import java.util.List;

@Component
public class StationClient {

    private final RestTemplate restTemplate;

    private final String ENDPOINT_STATIONS = "http://www.rmfon.pl/json/stations.txt";

    private final String ENDPOINT_STATIONS_OPENFM = "https://open.fm/api/static/stations/stations_new.json";

    @Autowired
    public StationClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Station> getStations(){
        ResponseEntity<List<Station>> responseEntity = restTemplate.exchange(ENDPOINT_STATIONS, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Station>>(){});
        return responseEntity.getBody();
    }

    public List<Station> getStationsOpenFm(){
        ResponseEntity<OpenFm> responseEntity = restTemplate.exchange(ENDPOINT_STATIONS_OPENFM, HttpMethod.GET, null, OpenFm.class);
        return responseEntity.getBody().getStations();
    }

    public List<Station> getAllStations() {
        List<Station> stations = getStations();
        stations.addAll(getStationsOpenFm());
        return stations;
    }

}
