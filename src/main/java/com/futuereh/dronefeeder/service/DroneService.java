package com.futuereh.dronefeeder.service;

import com.futuereh.dronefeeder.dto.DroneDto;
import com.futuereh.dronefeeder.model.Drone;
import com.futuereh.dronefeeder.repository.DroneRepository;
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
}
