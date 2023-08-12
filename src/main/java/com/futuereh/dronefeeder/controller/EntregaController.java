package com.futuereh.dronefeeder.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.futuereh.dronefeeder.model.Entrega;
import com.futuereh.dronefeeder.model.StatusEntrega;
import com.futuereh.dronefeeder.service.EntregaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "Drone Feeder", tags = {"Delivery"})
@RestController
@RequestMapping("/entregas")
public class EntregaController {

  @Autowired
  EntregaService entregaService;

  @GetMapping("/{id}")
  @ApiOperation(value = "Find all deliveries",
      notes = "Find all deliveries made by a drone and filtered by status")
  public ResponseEntity<List<Entrega>> findByDrone(
      @ApiParam(name = "id", value = "Drone id") @PathVariable("id") Long id,
      @ApiParam(name = "status", value = "Delivery status", required = false,
          allowableValues = "PENDENTE, ENTREGUE, NAOENTREGUE") @RequestParam Optional<String> status) {
    Optional<StatusEntrega> statusEntrega = status.map(this::toStatusEntrega);
    List<Entrega> entregas = this.entregaService.findByDrone(id, statusEntrega);
    return ResponseEntity.status(HttpStatus.OK).body(entregas);
  }

  private StatusEntrega toStatusEntrega(String status) {
    return StatusEntrega.valueOf(status);
  }

}
