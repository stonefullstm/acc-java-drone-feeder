package com.futuereh.dronefeeder.service;

import com.futuereh.dronefeeder.dto.DroneDto;
import com.futuereh.dronefeeder.exceptions.EntityNaoExistenteException;
import com.futuereh.dronefeeder.model.Drone;
import com.futuereh.dronefeeder.model.Entrega;
import com.futuereh.dronefeeder.repository.DroneRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DroneService {
  @Autowired
  private DroneRepository droneRepository;

  /**
   * save.
   */
  public Drone save(DroneDto droneDto) {
    Drone drone = new Drone();
    drone.setNome(droneDto.getNome());
    drone.setLatitude(droneDto.getLatitude());
    drone.setLongitude(droneDto.getLongitude());
    return this.droneRepository.save(drone);
  }

  /**
   * findAll.
   */
  public List<Drone> findAll() {
    return this.droneRepository.findAll();
  }

  /**
   * getById.
   */
  public Drone getById(Long id) {
    return this.droneRepository.findById(id).get();
  }

  /**
   * saveEntrega.
   */
  public Entrega saveEntrega(Long id) {
    Drone drone = this.droneRepository.findById(id)
        .orElseThrow(() -> new EntityNaoExistenteException("Drone não encontrado"));
    Entrega entrega = new Entrega();
    entrega.setDrone(drone);
    drone.addEntrega(entrega);
    this.droneRepository.save(drone);
    return entrega;
  }
}
