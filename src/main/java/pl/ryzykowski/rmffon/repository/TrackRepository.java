package pl.ryzykowski.rmffon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ryzykowski.rmffon.entity.TrackEntity;

import java.util.List;

public interface TrackRepository extends JpaRepository<TrackEntity, Long> {

    List<TrackEntity> findAll();

}
