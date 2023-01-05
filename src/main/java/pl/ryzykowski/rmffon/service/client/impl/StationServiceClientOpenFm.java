package pl.ryzykowski.rmffon.service.client.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ryzykowski.rmffon.client.OpenFmClient;
import pl.ryzykowski.rmffon.dto.StationDTO;
import pl.ryzykowski.rmffon.service.client.StationServiceClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationServiceClientOpenFm implements StationServiceClient {

    private OpenFmClient openFmClient;

    @Autowired
    public StationServiceClientOpenFm(OpenFmClient openFmClient) {
        this.openFmClient = openFmClient;
    }

    @Override
    public List<StationDTO> getStations() {
        return openFmClient.getStationsOpenFm()
                .stream()
                .map(item -> new StationDTO(item.getId(), item.getName(), "OPENFM"))
                .collect(Collectors.toList());
    }
}
