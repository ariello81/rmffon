package pl.ryzykowski.rmffon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/tracks/{radioService}/{stationId}")
    public List<TrackDTO> getTracks(@PathVariable("radioService") String radioService, @PathVariable("stationId") String stationId){
        return trackService.getTracks(radioService, stationId);
    }


}
