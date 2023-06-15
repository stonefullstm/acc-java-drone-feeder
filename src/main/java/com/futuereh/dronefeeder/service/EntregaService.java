package com.futuereh.dronefeeder.service;

import com.futuereh.dronefeeder.model.Entrega;
import com.futuereh.dronefeeder.repository.EntregaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntregaService {

  @Autowired
  private EntregaRepository entregaRepository;

  /**
   * findAll.
   */
  public List<Entrega> findAll() {
    return this.entregaRepository.findAll();
  }

}
