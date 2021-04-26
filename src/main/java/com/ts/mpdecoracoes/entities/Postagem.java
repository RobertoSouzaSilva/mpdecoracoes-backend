package com.ts.mpdecoracoes.entities;

import com.ts.mpdecoracoes.entities.enums.Categoria;
import com.ts.mpdecoracoes.entities.enums.Modelo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

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
