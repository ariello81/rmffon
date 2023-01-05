package pl.ryzykowski.rmffon.service.client;

import pl.ryzykowski.rmffon.dto.TrackDTO;

import java.util.List;

public interface TrackServiceClient {

    List<TrackDTO> getTracks(String stationId);

}
