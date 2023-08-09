package com.futuereh.dronefeeder.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.futuereh.dronefeeder.model.Entrega;
import com.futuereh.dronefeeder.service.EntregaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Drone Feeder", tags = {"Delivery"})
@RestController
@RequestMapping("/entregas")
public class EntregaController {

  @Autowired
  EntregaService entregaService;

  @GetMapping
  @ApiOperation(value = "Find all deliveries", notes = "Find all deliveries on database")
  public ResponseEntity<List<Entrega>> findAll() {
    List<Entrega> entregas = this.entregaService.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(entregas);
  }

}
