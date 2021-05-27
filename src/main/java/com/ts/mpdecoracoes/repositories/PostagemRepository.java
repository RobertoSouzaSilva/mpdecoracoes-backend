package com.ts.mpdecoracoes.repositories;

import com.ts.mpdecoracoes.entities.Postagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {


    @Query(value = "SELECT * FROM tb_postagem WHERE LOWER(descricao_postagem) LIKE %:descricao%", nativeQuery = true)
    List<Postagem> findBySlug(@Param("descricao") String descricao);

    @Query(value = "SELECT * FROM tb_postagem WHERE categoria LIKE %:categoria% AND modelo LIKE %:modelo% AND LOWER(descricao_postagem) LIKE %:descricao%", nativeQuery = true)
    Page<Postagem> find(Pageable pageRequest, @Param("categoria") String categoria, @Param("modelo") String modelo, @Param("descricao") String descricao);


}
