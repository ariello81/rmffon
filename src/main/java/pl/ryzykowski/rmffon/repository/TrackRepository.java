package pl.ryzykowski.rmffon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ryzykowski.rmffon.entity.TrackEntity;

public interface TrackRepository extends JpaRepository<TrackEntity, Long> {
}
