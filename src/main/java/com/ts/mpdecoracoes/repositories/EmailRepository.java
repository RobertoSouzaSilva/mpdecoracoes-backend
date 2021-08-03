package com.ts.mpdecoracoes.repositories;

import com.ts.mpdecoracoes.entities.Email;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
    
}
    

