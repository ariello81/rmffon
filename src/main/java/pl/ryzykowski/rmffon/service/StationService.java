package pl.ryzykowski.rmffon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ryzykowski.rmffon.dto.StationDTO;
import pl.ryzykowski.rmffon.service.client.StationServiceClient;

import java.util.List;

@Service
public class StationService {

    private StationServiceClient stationServiceClientRmf;

    @Autowired
    public StationService(StationServiceClient stationServiceClientRmf) {
        this.stationServiceClientRmf = stationServiceClientRmf;
    }

    public List<StationDTO> getStations(){
        return stationServiceClientRmf.getStations();
    }
}
