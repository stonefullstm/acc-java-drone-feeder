package com.futuereh.dronefeeder.dto;

import javax.validation.constraints.NotNull;
import com.futuereh.dronefeeder.model.StatusEntrega;

/**
 * Class StatusEntregaDto.
 *
 */
public class StatusEntregaDto {
  @NotNull(message = "Status value is required")
  private StatusEntrega status;

  /**
   * getStatus.
   */
  public StatusEntrega getStatus() {
    return status;
  }

  /**
   * setStatus.
   */
  public void setStatus(StatusEntrega status) {
    this.status = status;
  }

}
