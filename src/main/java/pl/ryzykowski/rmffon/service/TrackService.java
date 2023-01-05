package pl.ryzykowski.rmffon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ryzykowski.rmffon.dto.TrackDTO;
import pl.ryzykowski.rmffon.service.client.TrackServiceClient;

import java.util.ArrayList;
import java.util.List;


@Service
public class TrackService {

    private TrackServiceClient trackClientRmf;
    private TrackServiceClient trackClientOpenFm;

    @Autowired
    public TrackService(TrackServiceClient trackClientRmf, TrackServiceClient trackClientOpenFm) {
        this.trackClientRmf = trackClientRmf;
        this.trackClientOpenFm = trackClientOpenFm;
    }

    public List<TrackDTO> getTracks(String radioService, String stationId){
        if (radioService.equals("RMF")) {
            return trackClientRmf.getTracks(stationId);
        }
        else {
            return trackClientOpenFm.getTracks(stationId);
        }
    }

}
