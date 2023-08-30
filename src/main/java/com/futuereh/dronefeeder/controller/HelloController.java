package com.futuereh.dronefeeder.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

  @GetMapping
  private ResponseEntity<String> sayHello() {
    return ResponseEntity.ok().body("Bemvindo à API Drone Feeder");
  }

}
