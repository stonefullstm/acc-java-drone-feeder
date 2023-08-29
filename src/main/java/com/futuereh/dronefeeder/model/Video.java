package com.futuereh.dronefeeder.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity Video.
 *
 */
@Entity
public class Video {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  private String nomeArquivo;

  public Video() {
    super();

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
