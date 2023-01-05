package pl.ryzykowski.rmffon.service.client.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ryzykowski.rmffon.client.RmfClient;
import pl.ryzykowski.rmffon.dto.StationDTO;
import pl.ryzykowski.rmffon.service.client.StationServiceClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationServiceClientRmf implements StationServiceClient {

    private RmfClient rmfClient;

    @Autowired
    public StationServiceClientRmf(RmfClient rmfClient) {
        this.rmfClient = rmfClient;
    }

    @Override
    public List<StationDTO> getStations() {
        return rmfClient.getStations()
                .stream()
                .map(item -> new StationDTO(item.getId(), item.getName().replace("&amp;", "&"), "RMF"))
                .collect(Collectors.toList());
    }

}
