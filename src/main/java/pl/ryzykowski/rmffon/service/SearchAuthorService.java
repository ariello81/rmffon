package pl.ryzykowski.rmffon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ryzykowski.rmffon.aop.Timed;
import pl.ryzykowski.rmffon.dto.TrackDTO;
import pl.ryzykowski.rmffon.service.client.SearchAuthorServiceClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchAuthorService {

    List<SearchAuthorServiceClient> searchAuthorServiceClients;

    @Autowired
    public SearchAuthorService(List<SearchAuthorServiceClient> searchAuthorServiceClients) {
        this.searchAuthorServiceClients = searchAuthorServiceClients;
    }

    @Timed
    public List<TrackDTO> getAuthorTracks(String author){
        List<TrackDTO> tracks = new ArrayList<>();
        for (SearchAuthorServiceClient searchAuthorServiceClient : searchAuthorServiceClients){
            tracks.addAll(searchAuthorServiceClient.getAuthorTracks(author));
        }
        return tracks
                .stream()
                .sorted((TrackDTO t1, TrackDTO t2)->t1.getStart().compareTo(t2.getStart()))
                .collect(Collectors.toList());
    }

}
