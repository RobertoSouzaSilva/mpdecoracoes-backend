package com.ts.mpdecoracoes.repositories;

import com.ts.mpdecoracoes.entities.Regra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegraRepository extends JpaRepository<Regra, Long> {
    
}
