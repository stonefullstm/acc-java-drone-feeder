package com.futuereh.dronefeeder.dto;

import javax.validation.constraints.NotNull;

/**
 * Class DroneDto.
 *
 */
public class DroneDto {
  @NotNull(message = "Nome is required")
  private String nome;
  @NotNull(message = "Modelo is required")
  private String modelo;

  /**
   * getNome.
   */
  public String getNome() {
    return nome;
  }

  /**
   * setNome.
   */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * getModelo.
   */
  public String getModelo() {
    return modelo;
  }

  /**
   * setModelo.
   */
  public void setModelo(String modelo) {
    this.modelo = modelo;
  }

}
