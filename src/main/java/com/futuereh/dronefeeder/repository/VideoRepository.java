package com.futuereh.dronefeeder.repository;

import com.futuereh.dronefeeder.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * VideoRepository.
 */
@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

}
