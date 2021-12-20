package pl.ryzykowski.rmffon.service.client.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ryzykowski.rmffon.client.StationClient;
import pl.ryzykowski.rmffon.dto.StationDTO;
import pl.ryzykowski.rmffon.service.client.StationServiceClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationServiceClientRmf implements StationServiceClient {

    private StationClient stationClient;

    @Autowired
    public StationServiceClientRmf(StationClient stationClient) {
        this.stationClient = stationClient;
    }

    @Override
    public List<StationDTO> getStations() {
        return stationClient.getStations()
                .stream()
                .map(item -> new StationDTO(item.getId(), item.getName().replace("&amp;", "&"), "RMF"))
                .collect(Collectors.toList());
    }

}
