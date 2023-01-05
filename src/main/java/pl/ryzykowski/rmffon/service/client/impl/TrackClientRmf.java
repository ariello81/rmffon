package pl.ryzykowski.rmffon.service.client.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import pl.ryzykowski.rmffon.aop.Timed;
import pl.ryzykowski.rmffon.client.RmfClient;
import pl.ryzykowski.rmffon.dto.TrackDTO;
import pl.ryzykowski.rmffon.model.Station;
import pl.ryzykowski.rmffon.model.Track;
import pl.ryzykowski.rmffon.service.client.SearchAuthorServiceClient;
import pl.ryzykowski.rmffon.service.client.TrackServiceClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrackClientRmf implements SearchAuthorServiceClient, TrackServiceClient {

    private final String STATION_LINK_RMF = "https://www.rmfon.pl/play,";

    private RmfClient rmfClient;

    @Autowired
    public TrackClientRmf(RmfClient rmfClient) {
        this.rmfClient = rmfClient;
    }

    @Timed
    public List<TrackDTO> getAuthorTracks(String author)  {
        List<TrackDTO> tracks = Collections.synchronizedList(new ArrayList<>());
        List<Thread> threads = new ArrayList<>();
        for (Station station : rmfClient.getStations()) {
            Thread t = new Thread(() -> {
                tracks.addAll(this.getTracks(station.getId()).stream()
                        .filter(item -> item.getAuthor().toLowerCase().contains(author.toLowerCase()))
                        .collect(Collectors.toList()));
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
        return tracks;
    }


    public List<TrackDTO> getTracks(String stationId){
        return rmfClient.getTracks(stationId)
                .stream()
                .map(item -> new TrackDTO(item.getOrder(), stationId, stationName(stationId, rmfClient.getStations()),
                        item.getAuthor(), item.getAuthorUrl(), item.getTitle(), item.getRecordTitle(),
                        trackLengthRmf(item.getLenght()), item.getYear(), item.getStart(), item.getCoverUrl(),
                        item.getCoverBigUrl(), item.getVotes(), item.getPoints(), averagePoints(item),
                        STATION_LINK_RMF + stationId))
                .collect(Collectors.toList());
    }


    private String stationName(String stationId, List<Station> stations){
        return stations
                .stream()
                .filter(station -> station.getId().equals(stationId))
                .findFirst()
                .get()
                .getName()
                .replace("&amp;", "&");
    }

    private String averagePoints(Track track) {
        return String.format("%.2f", (float)track.getPoints()/track.getVotes());
    }

    private String trackLengthRmf(String length){
        if (!length.equals("")) {
            return trackLength(Integer.valueOf(length));
        }
        else {
            return "";
        }
    }

    private String trackLength(int length){
        return length/60 + ":" + String.format("%02d", length%60);
    }


}
