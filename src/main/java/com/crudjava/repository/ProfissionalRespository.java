package com.crudjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crudjava.model.Profissional;

@Repository
public interface ProfissionalRespository extends JpaRepository<Profissional, Long>{

}
