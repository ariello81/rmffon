package pl.ryzykowski.rmffon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ryzykowski.rmffon.client.StationClient;
import pl.ryzykowski.rmffon.client.TrackClient;
import pl.ryzykowski.rmffon.dto.TrackLiteDTO;
import pl.ryzykowski.rmffon.model.Station;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

@Service
public class TrackService {

    private TrackClient trackClient;
    private StationClient stationClient;

    private List<Station> stations;

    @Autowired
    public TrackService(TrackClient trackClient, StationClient stationClient) {
        this.trackClient = trackClient;
        this.stationClient = stationClient;
        this.stations = stationClient.getStations();
    }

    public List<TrackLiteDTO> getTracks(String stationId){
        return trackClient.getTracks(stationId)
                .stream()
                .map(item -> new TrackLiteDTO(item.getStart(), item.getAuthor(), item.getTitle(),
                        Integer.valueOf(item.getLenght())/60 + ":" + String.format("%02d", Integer.valueOf(item.getLenght())%60),
                        stations.stream().filter(station -> station.getId().equals(stationId)).findFirst().get().getName(),
                        item.getVotes(), item.getPoints(), String.format("%.2f", (float)item.getPoints()/item.getVotes())))
                .collect(Collectors.toList());
    }

    public List<TrackLiteDTO> getAuthorTracks(String author) {
        List<TrackLiteDTO> tracks = Collections.synchronizedList(new ArrayList<>());
        List<Thread> threads = new ArrayList<>();
        for (Station station : stations) {
            Thread t = new Thread(() -> {
                tracks.addAll(this.getTracks(station.getId()));
            });
            threads.add(t);
            t.start();
        }
        for (int i = 0; i < threads.size(); i++) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return tracks
                .stream()
                .filter(item -> item.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }


}
