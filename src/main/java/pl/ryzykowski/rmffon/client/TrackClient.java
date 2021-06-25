package pl.ryzykowski.rmffon.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.ryzykowski.rmffon.model.*;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrackClient {

    private final RestTemplate restTemplate;

    private final String ENDPOINT_TRACKS = "http://rmfon.pl/stacje/playlista_X.json.txt";

    private final String ENDPOINT_TRACKS_OPENFM = "https://open.fm/api/api-ext/v2/channels/long.json";

    @Autowired
    public TrackClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Track> getTracks(String stationId){
        ResponseEntity<List<Track>> responseEntity = restTemplate.exchange(ENDPOINT_TRACKS.replace("X", stationId),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Track>>(){});
        return responseEntity.getBody();
    }

    public List<OpenFmTrack> getTracksOpenFm(String stationId){
        ResponseEntity<OpenFmLong> responseEntity = restTemplate.exchange(ENDPOINT_TRACKS_OPENFM, HttpMethod.GET, null, OpenFmLong.class);
        return responseEntity.getBody().getChannels()
                .stream()
                .filter(channel -> channel.getId().equals(stationId))
                .collect(Collectors.toList())
                .get(0).getTracks();
    }

    public List<Channel> getChannelsOpenFm(){
        ResponseEntity<OpenFmLong> responseEntity = restTemplate.exchange(ENDPOINT_TRACKS_OPENFM, HttpMethod.GET, null, OpenFmLong.class);
        return responseEntity.getBody().getChannels();
    }

}
