package pl.ryzykowski.rmffon.service.client;

import pl.ryzykowski.rmffon.dto.StationDTO;

import java.util.List;

public interface StationServiceClient {

    List<StationDTO> getStations();

}
