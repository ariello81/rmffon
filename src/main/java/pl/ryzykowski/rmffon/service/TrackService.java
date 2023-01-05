package pl.ryzykowski.rmffon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ryzykowski.rmffon.dto.TrackDTO;
import pl.ryzykowski.rmffon.service.client.TrackServiceClient;

import java.util.List;


@Service
public class TrackService {

    private TrackServiceClient trackClientRmf;

    @Autowired
    public TrackService(TrackServiceClient trackClientRmf) {
        this.trackClientRmf = trackClientRmf;
    }

    public List<TrackDTO> getTracks(String radioService, String stationId){
        return trackClientRmf.getTracks(stationId);
    }

}
