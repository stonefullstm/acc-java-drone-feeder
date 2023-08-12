package com.futuereh.dronefeeder.repository;

import com.futuereh.dronefeeder.model.Drone;
import com.futuereh.dronefeeder.model.Entrega;
import com.futuereh.dronefeeder.model.StatusEntrega;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * EntregaRepository.
 */
@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {

  @Query("SELECT e FROM Entrega e WHERE (e.drone = :drone) AND (:status IS NULL OR e.status = :status)")
  List<Entrega> findByDroneAndOptionalFilters(Drone drone, Optional<StatusEntrega> status);
}
