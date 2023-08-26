package com.futuereh.dronefeeder.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.Instant;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Entity Entrega.
 *
 */
@Entity
public class Entrega {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column
  private double latitude;
  @Column
  private double longitude;
  @Column
  private String dataCriacao;
  @Column
  private String dataEntrega;
  @Column
  private StatusEntrega status;
  @JsonBackReference
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "drone_id")

  private Drone drone;
  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "video_id")
  @JsonIgnore
  private Video video;

  /**
   * Construtor.
   */
  public Entrega() {
    super();
    this.dataCriacao = Instant.now().toString();
    this.status = StatusEntrega.PENDENTE;
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

  /**
   * getDataCriacao.
   */
  public String getDataCriacao() {
    return dataCriacao;
  }

  /**
   * setDataCriacao.
   */
  public void setDataCriacao(String dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  /**
   * getId.
   */
  public Long getId() {
    return id;
  }

  /**
   * setId.
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * getDataEntrega.
   */
  public String getDataEntrega() {
    return dataEntrega;
  }

  /**
   * setDataEntrega.
   */
  public void setDataEntrega(String dataEntrega) {
    this.dataEntrega = dataEntrega;
  }

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
   * getDrone.
   */
  public Drone getDrone() {
    return drone;
  }

  /**
   * setDrone.
   */
  public void setDrone(Drone drone) {
    this.drone = drone;
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
