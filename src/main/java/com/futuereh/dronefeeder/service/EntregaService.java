package com.futuereh.dronefeeder.service;

import com.futuereh.dronefeeder.exceptions.EntityNaoExistenteException;
import com.futuereh.dronefeeder.model.Drone;
import com.futuereh.dronefeeder.model.Entrega;
import com.futuereh.dronefeeder.model.StatusEntrega;
import com.futuereh.dronefeeder.repository.DroneRepository;
import com.futuereh.dronefeeder.repository.EntregaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntregaService {

  private final String ERROR_MESSAGE = "Drone não encontrado";
  @Autowired
  private EntregaRepository entregaRepository;

  @Autowired
  private DroneRepository droneRepository;

  /**
   * findAll.
   */
  public List<Entrega> findByDrone(Long id, Optional<StatusEntrega> status) {
    Drone drone = this.droneRepository.findById(id)
        .orElseThrow(() -> new EntityNaoExistenteException(this.ERROR_MESSAGE));
    return this.entregaRepository.findByDroneAndOptionalFilters(drone, status);
  }

}
