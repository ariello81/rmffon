package pl.ryzykowski.rmffon.service.client.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.ryzykowski.rmffon.aop.Timed;
import pl.ryzykowski.rmffon.client.OpenFmClient;
import pl.ryzykowski.rmffon.dto.TrackDTO;
import pl.ryzykowski.rmffon.model.Channel;
import pl.ryzykowski.rmffon.model.OpenFmTrack;
import pl.ryzykowski.rmffon.model.Station;
import pl.ryzykowski.rmffon.service.TrackService;
import pl.ryzykowski.rmffon.service.client.SearchAuthorServiceClient;
import pl.ryzykowski.rmffon.service.client.TrackServiceClient;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrackClientOpenFm implements SearchAuthorServiceClient, TrackServiceClient {

    private final String STATION_LINK_OPENFM = "https://open.fm/stacja/";

    private OpenFmClient openFmClient;

    @Autowired
    public TrackClientOpenFm(OpenFmClient openFmClient) {
        this.openFmClient = openFmClient;
    }

    @Timed
    public List<TrackDTO> getAuthorTracks(String author)  {
        return getAllTracksOpenFm()
                .stream()
                .filter(item -> item.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<TrackDTO> getTracks(String stationId) {
        return openFmClient.getTracksOpenFm(stationId)
                .stream()
                .map(item -> new TrackDTO(checkIsCurrent(item.getEnd(), item.getBegin()), stationId,
                        stationName(stationId, openFmClient.getStationsOpenFm()), item.getSong().getArtist(),
                        "", item.getSong().getTitle(), item.getSong().getAlbum().getTitle(),
                        trackLengthOpenFm(item.getEnd(), item.getBegin()),
                        Integer.valueOf(item.getSong().getAlbum().getYear()), timeOfStart(item.getBegin()),
                        "", "", 0, 0, "",
                        STATION_LINK_OPENFM + stationNameLink(stationId, openFmClient.getStationsOpenFm())))
                .collect(Collectors.toList());
    }

    public List<TrackDTO> getAllTracksOpenFm() {
        List<TrackDTO> tracks = new ArrayList<>();
        List<Station> stationsOpenFm = openFmClient.getStationsOpenFm();
        for (Channel channel : openFmClient.getChannelsOpenFm()) {
            for (OpenFmTrack track : channel.getTracks()) {
                tracks.add(new TrackDTO(checkIsCurrent(track.getEnd(), track.getBegin()), channel.getId(),
                        stationName(channel.getId(), stationsOpenFm), track.getSong().getArtist(),
                        "", track.getSong().getTitle(), track.getSong().getAlbum().getTitle(),
                        trackLengthOpenFm(track.getEnd(), track.getBegin()),
                        Integer.valueOf(track.getSong().getAlbum().getYear()), timeOfStart(track.getBegin()),
                        "", "", 0, 0, "",
                        STATION_LINK_OPENFM + stationNameLink(channel.getId(), stationsOpenFm)));
            }
        }
        return tracks;
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

    private String timeOfStart(double begin) {
        Instant instant = Instant.ofEpochSecond((int) Math.round(begin));
        LocalDateTime localDateTime
                = LocalDateTime.ofInstant(instant, ZoneId.of("Europe/Berlin"));
        String pattern = "HH:mm:ss";
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    private int checkIsCurrent(double end, double begin) {
        Date date = new Date();
        long unixTime = System.currentTimeMillis() / 1000L;
        if (unixTime<begin) return 1;
        else if (unixTime>end) return -1;
        else return 0;
    }


}
