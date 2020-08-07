package pl.ryzykowski.rmffon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.ryzykowski.rmffon.dto.TrackDTO;
import pl.ryzykowski.rmffon.entity.TrackEntity;
import pl.ryzykowski.rmffon.repository.TrackRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackSavedService {

    private TrackService trackService;
    private TrackRepository trackRepository;

    @Autowired
    public TrackSavedService(TrackService trackService, TrackRepository trackRepository) {
        this.trackService = trackService;
        this.trackRepository = trackRepository;
    }

    public List<TrackEntity> getAllTracks(){
        return trackRepository.getAll();
    }

    @Scheduled(cron = "0 0,30 * * * ?")
    public void getColdplayTracks(){
        List<TrackDTO> tracks = trackService.getAuthorTracks("coldplay");
        trackRepository.saveAll(
                tracks.stream()
                        .map(item -> new TrackEntity(item.getStationName(), item.getAuthor(), item.getTitle(),
                                item.getRecordTitle(), item.getLenght(), item.getYear(), item.getStart(), getStrDate()))
                .collect(Collectors.toList())
        );
    }

    @Scheduled(cron = "0 10,40 * * * ?")
    public void getU2Tracks(){
        List<TrackDTO> tracks = trackService.getAuthorTracks("u2");
        trackRepository.saveAll(
                tracks.stream()
                        .map(item -> new TrackEntity(item.getStationName(), item.getAuthor(), item.getTitle(),
                                item.getRecordTitle(), item.getLenght(), item.getYear(), item.getStart(), getStrDate()))
                        .collect(Collectors.toList())
        );
    }

    @Scheduled(cron = "0 20,50 * * * ?")
    public void getOasisTracks(){
        List<TrackDTO> tracks = trackService.getAuthorTracks("oasis");
        trackRepository.saveAll(
                tracks.stream()
                        .map(item -> new TrackEntity(item.getStationName(), item.getAuthor(), item.getTitle(),
                                item.getRecordTitle(), item.getLenght(), item.getYear(), item.getStart(), getStrDate()))
                        .collect(Collectors.toList())
        );
    }

    private String getStrDate(){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String strDate = dateFormat.format(date);
        return strDate;
    }


}
