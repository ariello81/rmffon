package pl.ryzykowski.rmffon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ryzykowski.rmffon.client.StationClient;
import pl.ryzykowski.rmffon.dto.StationDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationService {

    private StationClient stationClient;

    @Autowired
    public StationService(StationClient stationClient) {
        this.stationClient = stationClient;
    }

    public List<StationDTO> getStations(){
        return stationClient.getStations()
                .stream()
                .map(item -> new StationDTO(item.getId(), item.getName(), "http://localhost:8080/api/tracks/"+item.getId()))
                .collect(Collectors.toList());
    }
}
