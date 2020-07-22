package pl.ryzykowski.rmffon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ryzykowski.rmffon.client.StationClient;
import pl.ryzykowski.rmffon.dto.StationDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class StationController {

    private StationClient stationClient;

    @Autowired
    public StationController(StationClient stationClient) {
        this.stationClient = stationClient;
    }

    @GetMapping("/stations")
    public List<StationDTO> getStations(){
        return stationClient.getStations()
                .stream()
                .map(item -> new StationDTO(item.getId(), item.getName(), "http://localhost:8080/api/tracks/"+item.getId()))
                .collect(Collectors.toList());
    }
}
