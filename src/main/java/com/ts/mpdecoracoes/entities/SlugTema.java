package com.ts.mpdecoracoes.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "tb_slug_tema")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlugTema implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String slugTemaNome;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dataCriacao;

    @PrePersist
    public void prePersist() {
        dataCriacao = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        dataCriacao = Instant.now();
    }


}
