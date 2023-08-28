package com.futuereh.dronefeeder;

// import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.futuereh.dronefeeder.dto.StatusEntregaDto;
import com.futuereh.dronefeeder.model.Drone;
import com.futuereh.dronefeeder.model.Entrega;
import com.futuereh.dronefeeder.model.StatusEntrega;
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

  // @BeforeEach
  // public void setup() {
  // entregaRepository.deleteAll();
  // droneRepository.deleteAll();
  // }

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

  @Test
  @Order(2)
  @DisplayName("2 - Deve retornar todas as entregas relativas a um drone")
  void deveRetornarAsEntregasDeUmDrone() throws Exception {
    Drone drone = droneRepository.findAll().get(0);
    Entrega entrega1 = new Entrega();
    entrega1.setLatitude(1.0);
    entrega1.setLongitude(-1.0);
    entrega1.setDrone(drone);
    drone.addEntrega(entrega1);
    Entrega entrega2 = new Entrega();
    entrega2.setLatitude(1.5);
    entrega2.setLongitude(-1.0);
    entrega2.setDrone(drone);
    drone.addEntrega(entrega2);

    droneRepository.save(drone);
    mockMvc.perform(get("/entregas/" + drone.getId()).contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)));

  }

  @Test
  @Order(3)
  @DisplayName("3 - Deve alterar o status de uma entrega PENDENTE para ENTREGUE")
  void deveAlterarStatusEntrega() throws Exception {
    StatusEntregaDto statusEntregaDto = new StatusEntregaDto();
    statusEntregaDto.setStatus(StatusEntrega.ENTREGUE);
    Entrega entrega = droneRepository.findAll().get(0).getEntregas().get(0);
    mockMvc
        .perform(put("/entregas/" + entrega.getId()).contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(statusEntregaDto)))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$.status").value("ENTREGUE"));
  }

  @Test
  @Order(4)
  @DisplayName("4 - Deve retornar todas as entregas com status ENTREGUE relativas a um drone")
  void deveRetornarAsEntregasComStatusEntregueDeUmDrone() throws Exception {
    Drone drone = droneRepository.findAll().get(0);
    mockMvc
        .perform(get("/entregas/" + drone.getId() + "?status=ENTREGUE")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)));
  }
}
