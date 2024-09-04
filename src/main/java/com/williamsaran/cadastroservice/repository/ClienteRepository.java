package com.williamsaran.cadastroservice.repository;

import com.williamsaran.cadastroservice.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}