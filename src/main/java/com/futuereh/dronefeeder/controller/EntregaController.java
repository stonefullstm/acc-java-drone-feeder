package com.futuereh.dronefeeder.controller;

import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.futuereh.dronefeeder.dto.StatusEntregaDto;
import com.futuereh.dronefeeder.dto.VideoDto;
import com.futuereh.dronefeeder.model.Drone;
import com.futuereh.dronefeeder.model.Entrega;
import com.futuereh.dronefeeder.model.StatusEntrega;
import com.futuereh.dronefeeder.model.Video;
import com.futuereh.dronefeeder.service.EntregaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiParam;

@Api(value = "Drone Feeder", tags = {"Delivery"})
@RestController
@RequestMapping("/entregas")
public class EntregaController {

  @Autowired
  EntregaService entregaService;

  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 400, message = "Parâmetro inválido")})
  @GetMapping("/{id}")
  @ApiOperation(value = "Find all deliveries",
      notes = "Find all deliveries made by a drone and filtered by status", response = Drone.class)
  public ResponseEntity<List<Entrega>> findByDrone(
      @ApiParam(name = "id", value = "Drone id") @PathVariable("id") Long id,
      @ApiParam(name = "status", value = "Delivery status", required = false,
          allowableValues = "PENDENTE, ENTREGUE, NAOENTREGUE") @RequestParam Optional<String> status) {
    Optional<StatusEntrega> statusEntrega = status.map(this::toStatusEntrega);
    List<Entrega> entregas = this.entregaService.findByDrone(id, statusEntrega);
    return ResponseEntity.status(HttpStatus.OK).body(entregas);
  }

  @PutMapping("/{id}")
  @ApiOperation(value = "Update delivery status", notes = "Update status of an existing delivery")
  public ResponseEntity<Entrega> update(@PathVariable("id") Long id,
      @RequestBody StatusEntregaDto statusEntregaDto) {
    Entrega entrega = this.entregaService.update(id, statusEntregaDto);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(entrega);
  }

  @DeleteMapping("/{id}")
  @ApiOperation(value = "Remove a delivery", notes = "Remove an existing delivery")
  public ResponseEntity<String> delete(@PathVariable("id") Long id) {
    this.entregaService.delete(id);
    return ResponseEntity.status(HttpStatus.OK).body("Id removido: " + id);
  }

  @PostMapping("/{id}/video")
  @ApiOperation(value = "Save a video", notes = "Save a video to an existing delivery")
  public ResponseEntity<Video> saveVideo(@PathVariable("id") Long id,
      @Valid @RequestBody VideoDto videoDto) {
    Video video = this.entregaService.saveVideo(id, videoDto);
    return ResponseEntity.status(HttpStatus.OK).body(video);
  }

  private StatusEntrega toStatusEntrega(String status) {
    return StatusEntrega.valueOf(status);
  }

}
