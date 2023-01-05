package pl.ryzykowski.rmffon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ryzykowski.rmffon.dto.StationDTO;
import pl.ryzykowski.rmffon.service.client.StationServiceClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class StationService {

    //private List<StationServiceClient> stationServiceClients;
    private StationServiceClient stationServiceClientRmf;

    @Autowired
    public StationService(StationServiceClient stationServiceClientRmf) {
        this.stationServiceClientRmf = stationServiceClientRmf;
    }

    /*
    public StationService(List<StationServiceClient> stationServiceClients) {
        this.stationServiceClients = stationServiceClients;
    }*/

    public List<StationDTO> getStations(){
        List<StationDTO> stations = new ArrayList<>();
        stationServiceClientRmf.getStations();
        /*for (StationServiceClient stationServiceClient : stationServiceClients) {
            stations.addAll(stationServiceClient.getStations());
        }*/
        return stations;
    }
}
