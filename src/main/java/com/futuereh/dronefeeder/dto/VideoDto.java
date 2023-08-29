package com.futuereh.dronefeeder.dto;

import javax.validation.constraints.NotNull;

public class VideoDto {
  @NotNull(message = "Full file path is required")
  private String nomeArquivo;

  /**
   * getNomeArquivo.
   */
  public String getNomeArquivo() {
    return nomeArquivo;
  }

  /**
   * setNomeArquivo.
   */
  public void setNomeArquivo(String nomeArquivo) {
    this.nomeArquivo = nomeArquivo;
  }

}
