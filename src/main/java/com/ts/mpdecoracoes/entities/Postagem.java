package com.ts.mpdecoracoes.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_postagem")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Postagem implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricaoPostagem;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slug_tema_id")
    private SlugTema slugTema;
    private String urlImg;
    private String categoria;
    private String modelo;

}
