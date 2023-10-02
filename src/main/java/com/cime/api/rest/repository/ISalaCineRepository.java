package com.cime.api.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cime.api.rest.entities.SalaCine;

public interface ISalaCineRepository extends JpaRepository<SalaCine, Long>{

}
