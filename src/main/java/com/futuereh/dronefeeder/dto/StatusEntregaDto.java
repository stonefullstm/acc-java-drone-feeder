package com.futuereh.dronefeeder.dto;

import javax.validation.constraints.NotNull;
import com.futuereh.dronefeeder.model.StatusEntrega;
import com.futuereh.dronefeeder.model.Video;

/**
 * Class StatusEntregaDto.
 *
 */
public class StatusEntregaDto {
  @NotNull(message = "Status value is required")
  private StatusEntrega status;
  private Video video;

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

  /**
   * getVideo.
   */
  public Video getVideo() {
    return video;
  }

  /**
   * setVideo.
   */
  public void setVideo(Video video) {
    this.video = video;
  }

}
