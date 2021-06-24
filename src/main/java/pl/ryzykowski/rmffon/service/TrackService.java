package pl.ryzykowski.rmffon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ryzykowski.rmffon.aop.Timed;
import pl.ryzykowski.rmffon.client.StationClient;
import pl.ryzykowski.rmffon.client.TrackClient;
import pl.ryzykowski.rmffon.dto.TrackDTO;
import pl.ryzykowski.rmffon.model.Station;
import pl.ryzykowski.rmffon.model.Track;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackService {

    private TrackClient trackClient;
    private StationClient stationClient;

    private List<Station> stations;
    private List<Station> stationsOpenFm;

    @Autowired
    public TrackService(TrackClient trackClient, StationClient stationClient) {
        this.trackClient = trackClient;
        this.stationClient = stationClient;
        this.stations = stationClient.getStations();
        this.stationsOpenFm = stationClient.getStationsOpenFm();
    }

    public List<TrackDTO> getTracks(String radioService, String stationId){
        if (radioService.equals("RMF")) {
            return trackClient.getTracks(stationId)
                    .stream()
                    .map(item -> new TrackDTO(item.getOrder(), stationId, stationName(stationId, stations), item.getAuthor(),
                            item.getAuthorUrl(), item.getTitle(), item.getRecordTitle(), trackLength(item.getLenght()),
                            item.getYear(), item.getStart(), item.getCoverUrl(), item.getCoverBigUrl(),
                            item.getVotes(), item.getPoints(), averagePoints(item)))
                    .collect(Collectors.toList());
        }
        else {
            return trackClient.getTracksOpenFm(stationId)
                    .stream()
                    .map(item -> new TrackDTO(0, stationId, stationName(stationId, stationsOpenFm), item.getSong().getArtist(),
                            "", item.getSong().getTitle(), item.getSong().getAlbum().getTitle(), "0:00",
                            Integer.valueOf(item.getSong().getAlbum().getYear()), "0:00", "", "",
                            0, 0, ""))
                    .collect(Collectors.toList());
        }
    }


    @Timed
    public List<TrackDTO> getAuthorTracks(String author)  {
        List<TrackDTO> tracks = Collections.synchronizedList(new ArrayList<>());
        List<Thread> threads = new ArrayList<>();
        for (Station station : stations) {
            Thread t = new Thread(() -> {
                tracks.addAll(this.getTracks("RMF", station.getId()));
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
                .collect(Collectors.toList())
                .stream()
                .sorted((TrackDTO t1, TrackDTO t2)->t1.getStart().compareTo(t2.getStart()))
                .collect(Collectors.toList());
    }


    private String trackLength(String length){
        if (!length.equals("")) {
            return Integer.valueOf(length)/60 + ":" + String.format("%02d", Integer.valueOf(length)%60);
        }
        else {
            return "";
        }
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


}
