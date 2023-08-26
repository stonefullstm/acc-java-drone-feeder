package com.futuereh.dronefeeder.dto;

import javax.validation.constraints.NotNull;


/**
 * Class EntregaDto.
 *
 */
public class EntregaDto {
  @NotNull(message = "Latitude is required")
  private double latitude;
  @NotNull(message = "Longitude is required")
  private double longitude;

  /**
   * getLatitude.
   */
  public double getLatitude() {
    return latitude;
  }

  /**
   * setLatitude.
   */
  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  /**
   * getLongitude.
   */
  public double getLongitude() {
    return longitude;
  }

  /**
   * setLongitude.
   */
  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }


}
