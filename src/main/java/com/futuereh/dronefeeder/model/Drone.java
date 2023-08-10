package com.futuereh.dronefeeder.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
  @Column
  private String modelo;
  @JsonManagedReference
  @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL, orphanRemoval = true,
      fetch = FetchType.EAGER)
  private List<Entrega> entregas;

  public Drone() {
    super();
    this.entregas = new ArrayList<Entrega>();
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

  /**
   * getEntregas.
   */
  public List<Entrega> getEntregas() {
    return entregas;
  }

  /**
   * addEntrega.
   */
  public void addEntrega(Entrega entrega) {
    this.entregas.add(entrega);
  }

}
