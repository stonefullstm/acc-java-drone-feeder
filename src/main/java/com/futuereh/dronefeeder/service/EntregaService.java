package com.futuereh.dronefeeder.service;

import com.futuereh.dronefeeder.dto.StatusEntregaDto;
import com.futuereh.dronefeeder.dto.VideoDto;
import com.futuereh.dronefeeder.exceptions.EntityNaoExistenteException;
import com.futuereh.dronefeeder.model.Drone;
import com.futuereh.dronefeeder.model.Entrega;
import com.futuereh.dronefeeder.model.StatusEntrega;
import com.futuereh.dronefeeder.model.Video;
import com.futuereh.dronefeeder.repository.DroneRepository;
import com.futuereh.dronefeeder.repository.EntregaRepository;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntregaService {

  private final String errorMessage = "Drone não encontrado";
  private final String errorMessageEntrega = "Entrega não encontrada";
  @Autowired
  private EntregaRepository entregaRepository;

  @Autowired
  private DroneRepository droneRepository;

  /**
   * findAll.
   */
  public List<Entrega> findByDrone(Long id, Optional<StatusEntrega> status) {
    Drone drone = this.droneRepository.findById(id)
        .orElseThrow(() -> new EntityNaoExistenteException(this.errorMessage));
    return this.entregaRepository.findByDroneAndOptionalFilters(drone, status);
  }

  /**
   * update.
   */
  public Entrega update(Long id, StatusEntregaDto statusEntregaDto) {
    Entrega entrega = this.entregaRepository.findById(id)
        .orElseThrow(() -> new EntityNaoExistenteException(this.errorMessageEntrega));
    entrega.setStatus(statusEntregaDto.getStatus());
    if (statusEntregaDto.getStatus().equals(StatusEntrega.ENTREGUE)) {
      entrega.setDataEntrega(Instant.now().toString());
    }
    return this.entregaRepository.save(entrega);
  }

  /**
   * delete.
   */
  public void delete(Long id) {
    Entrega entrega = this.entregaRepository.findById(id)
        .orElseThrow(() -> new EntityNaoExistenteException(this.errorMessageEntrega));
    Drone drone = entrega.getDrone();
    drone.removeEntrega(entrega);
    this.entregaRepository.deleteById(id);
  }

  /**
   * saveVideo.
   */
  public Video saveVideo(Long id, VideoDto videoDto) {
    Entrega entrega = this.entregaRepository.findById(id)
        .orElseThrow(() -> new EntityNaoExistenteException(this.errorMessageEntrega));
    Video video = new Video();
    video.setNomeArquivo(videoDto.getNomeArquivo());
    entrega.setVideo(video);
    Entrega savedEntrega = entregaRepository.save(entrega);
    return savedEntrega.getVideo();
  }
}
