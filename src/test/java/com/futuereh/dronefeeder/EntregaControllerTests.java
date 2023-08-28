package com.futuereh.dronefeeder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.futuereh.dronefeeder.model.Drone;
import com.futuereh.dronefeeder.model.Entrega;
import com.futuereh.dronefeeder.repository.DroneRepository;
import com.futuereh.dronefeeder.repository.EntregaRepository;
import java.util.Optional;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtests.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EntregaControllerTests {
  @Autowired
  private MockMvc mockMvc;

  @SpyBean
  EntregaRepository entregaRepository;

  @SpyBean
  DroneRepository droneRepository;

  @Captor
  private ArgumentCaptor<Entrega> entregaCaptor;

  @Captor
  private ArgumentCaptor<Drone> droneCaptor;

  @BeforeEach
  public void setup() {
    entregaRepository.deleteAll();
  }

  @Test
  @Order(1)
  @DisplayName("1 - Deve remover uma entrega existente dado o seu id")
  void deveRemoverUmaEntregaExistenteNaBase() throws Exception {
    Drone drone = new Drone();
    drone.setNome("Drone 1");

    droneRepository.save(drone);
    Entrega entrega = new Entrega();
    entrega.setLatitude(1.0);
    entrega.setLongitude(-1.0);
    entrega.setDrone(drone);
    drone.addEntrega(entrega);
    Drone droneSaved = droneRepository.save(drone);
    Long id = droneSaved.getEntregas().get(0).getId();
    mockMvc.perform(delete("/entregas/" + id)).andExpect(status().isOk());
    Optional<Drone> droneFound = droneRepository.findById(droneSaved.getId());
    assertTrue(!droneFound.isEmpty());
    assertEquals(droneFound.get().getEntregas().size(), 0);
  }
}
