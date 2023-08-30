package com.futuereh.dronefeeder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtests.properties")
public class HelloControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  @Order(1)
  @DisplayName("1 - Deve retornar /Bemvindo à API Drone Feeder/")
  void deveRetornarHello() throws Exception {
    mockMvc.perform(get("/hello")).andExpect(status().isOk())
        .andExpect(content().string("Bemvindo à API Drone Feeder"));
  }
}
