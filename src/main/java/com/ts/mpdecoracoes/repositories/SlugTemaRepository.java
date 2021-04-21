package com.ts.mpdecoracoes.repositories;

import com.ts.mpdecoracoes.entities.SlugTema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SlugTemaRepository extends JpaRepository<SlugTema, Long> {

    @Query(value = "SELECT * FROM tb_slug_tema WHERE LOWER(slug_tema_nome) LIKE %:slug_tema_nome%", nativeQuery = true)
    SlugTema findByTemaNome(@Param("slug_tema_nome") String slugTemaNome);
}
