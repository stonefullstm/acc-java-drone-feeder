package com.futuereh.dronefeeder.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * Entity Video.
 *
 */
@Entity
public class Video {
  // source: https://www.baeldung.com/hibernate-lob
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  private String nome;
  @Lob
  private byte[] video;

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
   * getVideo.
   */
  public byte[] getVideo() {
    return video;
  }

  /**
   * setVideo.
   */
  public void setVideo(byte[] video) {
    this.video = video;
  }

}
