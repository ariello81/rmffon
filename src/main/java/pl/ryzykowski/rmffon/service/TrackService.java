package pl.ryzykowski.rmffon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ryzykowski.rmffon.aop.Timed;
import pl.ryzykowski.rmffon.client.StationClient;
import pl.ryzykowski.rmffon.client.TrackClient;
import pl.ryzykowski.rmffon.dto.TrackDTO;
import pl.ryzykowski.rmffon.model.Channel;
import pl.ryzykowski.rmffon.model.OpenFmTrack;
import pl.ryzykowski.rmffon.model.Station;
import pl.ryzykowski.rmffon.model.Track;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackService {

    private TrackClient trackClient;
    private StationClient stationClient;

    private List<Station> stations;
    private List<Station> stationsOpenFm;

    private final String STATION_LINK_RMF = "https://www.rmfon.pl/play,";
    private final String STATION_LINK_OPENFM = "https://open.fm/stacja/";

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
                            item.getAuthorUrl(), item.getTitle(), item.getRecordTitle(), trackLengthRmf(item.getLenght()),
                            item.getYear(), item.getStart(), item.getCoverUrl(), item.getCoverBigUrl(),
                            item.getVotes(), item.getPoints(), averagePoints(item), STATION_LINK_RMF + stationId))
                    .collect(Collectors.toList());
        }
        else {
            return trackClient.getTracksOpenFm(stationId)
                    .stream()
                    .map(item -> new TrackDTO(checkIsCurrent(item.getEnd(), item.getBegin()), stationId, stationName(stationId, stationsOpenFm), item.getSong().getArtist(),
                            "", item.getSong().getTitle(), item.getSong().getAlbum().getTitle(), trackLengthOpenFm(item.getEnd(), item.getBegin()),
                            Integer.valueOf(item.getSong().getAlbum().getYear()), timeOfStart(item.getBegin()), "", "",
                            0, 0, "", STATION_LINK_OPENFM + stationNameLink(stationId, stationsOpenFm)))
                    .collect(Collectors.toList());
        }
    }


    public List<TrackDTO> getAllTracksOpenFm() {
        List<TrackDTO> tracks = new ArrayList<>();
        for (Channel channel : trackClient.getChannelsOpenFm()) {
            for (OpenFmTrack track : channel.getTracks()) {
                tracks.add(new TrackDTO(checkIsCurrent(track.getEnd(), track.getBegin()), channel.getId(), stationName(channel.getId(), stationsOpenFm), track.getSong().getArtist(),
                        "", track.getSong().getTitle(), track.getSong().getAlbum().getTitle(), trackLengthOpenFm(track.getEnd(), track.getBegin()),
                        Integer.valueOf(track.getSong().getAlbum().getYear()), timeOfStart(track.getBegin()), "", "",
                        0, 0, "", STATION_LINK_OPENFM + stationNameLink(channel.getId(), stationsOpenFm)));
            }
        }
        return tracks;
    }

    private int checkIsCurrent(double end, double begin) {
        Date date = new Date();
        long unixTime = System.currentTimeMillis() / 1000L;
        if (unixTime<begin) return 1;
        else if (unixTime>end) return -1;
        else return 0;
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

        tracks.addAll(this.getAllTracksOpenFm());

        return tracks
                .stream()
                .filter(item -> item.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList())
                .stream()
                .sorted((TrackDTO t1, TrackDTO t2)->t1.getStart().compareTo(t2.getStart()))
                .collect(Collectors.toList());
    }



    private String trackLengthRmf(String length){
        if (!length.equals("")) {
            return trackLength(Integer.valueOf(length));
        }
        else {
            return "";
        }
    }

    private String trackLengthOpenFm(double endTimestamp, double beginTimestamp){
        return trackLength((int) Math.round(endTimestamp - beginTimestamp));
    }

    private String trackLength(int length){
        return length/60 + ":" + String.format("%02d", length%60);
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

    private String stationNameLink(String stationId, List<Station> stations){
        return stationName(stationId, stations).toLowerCase()
                .replace(" ", "-")
                .replace(":", "")
                .replace("#", "")
                .replace("é", "e");
    }

    private String averagePoints(Track track) {
        return String.format("%.2f", (float)track.getPoints()/track.getVotes());
    }

    private String timeOfStart(double begin) {
        Instant instant = Instant.ofEpochSecond((int) Math.round(begin));
        Date date = Date.from( instant );
        String pattern = "HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

}
