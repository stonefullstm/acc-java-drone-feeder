package com.futuereh.dronefeeder.dto;

/**
 * Class DroneDto.
 *
 */
public class DroneDto {
  private String nome;
  private double latitude;
  private double longitude;

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
