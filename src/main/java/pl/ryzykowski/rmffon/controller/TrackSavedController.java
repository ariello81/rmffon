package pl.ryzykowski.rmffon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ryzykowski.rmffon.entity.TrackEntity;
import pl.ryzykowski.rmffon.service.TrackSavedService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TrackSavedController {

    private TrackSavedService trackSavedService;

    @Autowired
    public TrackSavedController(TrackSavedService trackSavedService) {
        this.trackSavedService = trackSavedService;
    }

    @GetMapping("/saved")
    public List<TrackEntity> getSavedTracks(){
        return trackSavedService.getAllTracks();
    }
}
