package com.futuereh.dronefeeder.controller;

import com.futuereh.dronefeeder.dto.DroneDto;
import com.futuereh.dronefeeder.model.Drone;
import com.futuereh.dronefeeder.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drones")
public class DroneController {

  @Autowired
  DroneService droneService;

  @PostMapping
  public ResponseEntity<Drone> save(@RequestBody DroneDto droneDto) {
    Drone drone = this.droneService.save(droneDto);
    return ResponseEntity.status(HttpStatus.OK).body(drone);
  }
}
