package com.futuereh.dronefeeder.controller;

import com.futuereh.dronefeeder.dto.DroneDto;
import com.futuereh.dronefeeder.dto.EntregaDto;
import com.futuereh.dronefeeder.model.Drone;
import com.futuereh.dronefeeder.model.Entrega;
import com.futuereh.dronefeeder.service.DroneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Drone Feeder", tags = {"Drone"})
@RestController
@RequestMapping("/drones")
public class DroneController {

  @Autowired
  DroneService droneService;

  @PostMapping
  @ApiOperation(value = "Save a drone", notes = "Save a new drone and returns all its data")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 400, message = "Bad Request")})
  public ResponseEntity<Drone> save(@Valid @RequestBody DroneDto droneDto) {
    Drone drone = this.droneService.save(droneDto);
    return ResponseEntity.status(HttpStatus.OK).body(drone);
  }

  @GetMapping
  @ApiOperation(value = "Find all drones", notes = "Find all drones on database")
  public ResponseEntity<List<Drone>> findAll() {
    List<Drone> drones = this.droneService.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(drones);
  }

  @PostMapping("/{id}/entrega")
  @ApiOperation(value = "Save a delivery", notes = "Save a delivery to an existing drone")
  public ResponseEntity<Entrega> saveEntrega(@PathVariable("id") Long id,
      @Valid @RequestBody EntregaDto entregaDto) {
    Entrega entrega = this.droneService.saveEntrega(id, entregaDto);
    return ResponseEntity.status(HttpStatus.OK).body(entrega);
  }

  @DeleteMapping("/{id}")
  @ApiOperation(value = "Remove a drone", notes = "Remove an existing drone")
  public ResponseEntity<String> delete(@PathVariable("id") Long id) {
    this.droneService.delete(id);
    return ResponseEntity.status(HttpStatus.OK).body("Id removido: " + id);
  }

  @PutMapping("/{id}")
  @ApiResponses(value = {@ApiResponse(code = 204, message = "OK"),
      @ApiResponse(code = 400, message = "Bad Request")})
  @ApiOperation(value = "Update a drone", notes = "Update an existing drone")
  public ResponseEntity<Drone> update(@PathVariable("id") Long id,
      @Valid @RequestBody DroneDto droneDto) {
    Drone drone = this.droneService.update(id, droneDto);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(drone);
  }
}
