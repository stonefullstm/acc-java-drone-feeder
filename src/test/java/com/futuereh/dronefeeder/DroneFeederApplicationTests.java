package com.futuereh.dronefeeder;

import com.futuereh.dronefeeder.dto.DroneDto;
import com.futuereh.dronefeeder.model.Drone;
import com.futuereh.dronefeeder.repository.DroneRepository;
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
    droneDto.setLatitude(1);
    droneDto.setLongitude(0);
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
}
