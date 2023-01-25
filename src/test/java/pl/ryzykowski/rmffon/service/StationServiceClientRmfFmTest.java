package pl.ryzykowski.rmffon.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.mockito.junit.jupiter.MockitoExtension;
import pl.ryzykowski.rmffon.client.RmfClient;
import pl.ryzykowski.rmffon.dto.StationDTO;
import pl.ryzykowski.rmffon.model.Station;
import pl.ryzykowski.rmffon.service.client.impl.StationServiceClientRmf;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class) // <- uncomment if setUp method is commented
public class StationServiceClientRmfFmTest {

    @Mock
    RmfClient rmfClient;

    @InjectMocks
    StationServiceClientRmf stationServiceClientRmf;

    /*@BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // <- uncomment if @ExtendsWith is commented
    }*/

    @Test
    public void should_return_formatted_stations(){
        given(rmfClient.getStations()).willReturn(listStations());
        List<StationDTO> actual = stationServiceClientRmf.getStations();
        List<StationDTO> expected = listStationsDTO();
        assertThat(actual).isEqualTo(expected);
    }

    private List<Station> listStations(){
        List<Station> list = new ArrayList<>();
        list.add(new Station("rmf", "RMF Ballady"));
        list.add(new Station("rmf-fm", "RMF&amp;Test"));
        return list;
    }

    private List<StationDTO> listStationsDTO(){
        List<StationDTO> list = new ArrayList<>();
        list.add(new StationDTO("rmf", "RMF Ballady", "RMF"));
        list.add(new StationDTO("rmf-fm", "RMF&Test", "RMF"));
        return list;
    }

}
