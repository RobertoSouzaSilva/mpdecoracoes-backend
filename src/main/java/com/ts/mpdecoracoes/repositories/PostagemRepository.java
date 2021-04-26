package com.ts.mpdecoracoes.repositories;

import com.ts.mpdecoracoes.entities.Postagem;
import com.ts.mpdecoracoes.entities.SlugTema;
import com.ts.mpdecoracoes.entities.enums.Categoria;
import com.ts.mpdecoracoes.entities.enums.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {

    @Query(value = "SELECT * FROM tb_postagem WHERE LOWER(descricao_postagem) LIKE %:descricao%", nativeQuery = true)
    Postagem findBySlug(@Param("descricao") String descricao);
}
