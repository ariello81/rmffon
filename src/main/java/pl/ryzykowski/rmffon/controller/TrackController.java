package pl.ryzykowski.rmffon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ryzykowski.rmffon.dto.TrackDTO;
import pl.ryzykowski.rmffon.service.TrackService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TrackController {

    private TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping("/tracks/{stationId}")
    public List<TrackDTO> getTracks(@PathVariable("stationId") String stationId){
        return trackService.getTracks(stationId);
    }

    @GetMapping("/search/{author}")
    public List<TrackDTO> getAllTracks(@PathVariable("author") String author){
        return trackService.getAuthorTracks(author);
    }



}
