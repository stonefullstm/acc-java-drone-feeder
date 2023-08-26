package com.futuereh.dronefeeder.dto;

import org.hibernate.validator.constraints.Range;


/**
 * Class EntregaDto.
 *
 */
public class EntregaDto {
  @Range(min = -90, max = 90, message = "Latitude must be between -90 and 90")
  private double latitude;
  @Range(min = -180, max = 180, message = "Longitude must be between -180 and 180")
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
