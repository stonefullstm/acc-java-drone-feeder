package com.futuereh.dronefeeder;

import com.futuereh.dronefeeder.dto.DroneDto;
import com.futuereh.dronefeeder.model.Drone;
import com.futuereh.dronefeeder.repository.DroneRepository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DroneFeederApplicationTests {
  @Autowired
  private MockMvc mockMvc;

  @SpyBean
  DroneRepository droneRepository;

  @Captor
  private ArgumentCaptor<Drone> droneCaptor;

  @BeforeEach
  public void setup() {
    droneRepository.deleteAll();
  }

  @Test
  @Order(1)
  @DisplayName("1 -  Deve adicionar um drone na base de dados.")
  void deveAdicionarDroneNaBaseDeDados() throws Exception {
    DroneDto droneDto = new DroneDto();
    droneDto.setNome("Drone 1");
    droneDto.setModelo("Modelo 1");
    mockMvc
        .perform(post("/drones").contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(droneDto)))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(jsonPath("$.nome").value(droneDto.getNome()));
  }

  @Test
  @Order(2)
  @DisplayName("2 - Deve retornar todas os drones existentes da base de dados.")
  void deveRetornarTodosDronesExistentesNaBase() throws Exception {
    Drone drone1 = new Drone();
    drone1.setNome("Drone 1");
    Drone drone2 = new Drone();
    drone2.setNome("Drone 2");
    droneRepository.save(drone1);
    droneRepository.save(drone2);

    mockMvc.perform(get("/drones").contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(jsonPath("$[0].nome").value(drone1.getNome()))
        .andExpect(jsonPath("$[1].nome").value(drone2.getNome()));
  }

  @Test
  @Order(3)
  @DisplayName("3 - Deve retornar lista vazia quando não existir drones na base de dados.")
  void deveRetornarListaVaziaQuandoNaoExistirDronesNaBase() throws Exception {
    mockMvc.perform(get("/drones").contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(content().string(containsString("[]")));
  }

  @Test
  @Order(4)
  @DisplayName("4 - Deve adicionar entrega quando existir drone na base de dados.")
  void deveAdicionarEntregaQuandoExistirDroneNaBaseDeDados() throws Exception {
    Drone drone = new Drone();
    drone.setNome("Drone 1");

    droneRepository.save(drone);
    // Entrega entrega = new Entrega();
    mockMvc
        .perform(
            post("/drones/" + drone.getId() + "/entrega").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(null)))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("PENDENTE"));
    verify(droneRepository, atLeast(1)).save(droneCaptor.capture());

    assertThat(droneCaptor.getValue()).isNotNull();
    assertThat(droneCaptor.getValue().getId()).isNotNull();
    assertThat(droneCaptor.getValue().getNome()).isEqualTo(drone.getNome());
    assertThat(droneCaptor.getValue().getEntregas()).hasSize(1);
  }
}
