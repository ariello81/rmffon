package pl.ryzykowski.rmffon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ryzykowski.rmffon.client.TrackClient;
import pl.ryzykowski.rmffon.dto.TrackLiteDTO;
import pl.ryzykowski.rmffon.model.Station;
import pl.ryzykowski.rmffon.model.Track;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TrackController {

    private TrackClient trackClient;

    @Autowired
    public TrackController(TrackClient trackClient) {
        this.trackClient = trackClient;
    }

    @GetMapping("/tracks/{stationId}")
    public List<TrackLiteDTO> getTracks(@PathVariable("stationId") String stationId){
        return trackClient.getTracks(stationId)
                .stream()
                .map(item -> new TrackLiteDTO(item.getStart(), item.getAuthor(), item.getTitle(), item.getLenght(), stationId, item.getVotes(), item.getPoints()))
                .collect(Collectors.toList());
    }

    @GetMapping("/tracks/all")
    public List<TrackLiteDTO> getAllTracks(){
        return trackClient.getAllTracks()
                .stream()
                .map(item -> new TrackLiteDTO(item.getStart(), item.getAuthor(), item.getTitle(), item.getLenght(), "", item.getVotes(), item.getPoints()))
                .collect(Collectors.toList());
    }


}
