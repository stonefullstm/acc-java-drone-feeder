package com.futuereh.dronefeeder.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity Drone.
 *
 */
@Entity
public class Drone {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  private String nome;

  /**
   * Construtor.
   */
  public Drone(Long id, String nome) {
    super();
    this.id = id;
    this.nome = nome;
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

}
