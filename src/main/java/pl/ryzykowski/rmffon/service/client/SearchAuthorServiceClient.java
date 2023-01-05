package pl.ryzykowski.rmffon.service.client;

import pl.ryzykowski.rmffon.dto.TrackDTO;

import java.util.List;

public interface SearchAuthorServiceClient {

    List<TrackDTO> getAuthorTracks(String author);

}
