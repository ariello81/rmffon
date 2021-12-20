package pl.ryzykowski.rmffon.service.client.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ryzykowski.rmffon.client.StationClient;
import pl.ryzykowski.rmffon.dto.StationDTO;
import pl.ryzykowski.rmffon.service.client.StationServiceClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationServiceClientOpenFm implements StationServiceClient {

    private StationClient stationClient;

    @Autowired
    public StationServiceClientOpenFm(StationClient stationClient) {
        this.stationClient = stationClient;
    }

    @Override
    public List<StationDTO> getStations() {
        return stationClient.getStationsOpenFm()
                .stream()
                .map(item -> new StationDTO(item.getId(), item.getName(), "OPENFM"))
                .collect(Collectors.toList());
    }
}
