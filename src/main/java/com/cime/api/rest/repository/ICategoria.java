package com.cime.api.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cime.api.rest.entities.Categoria;

public interface ICategoria extends JpaRepository<Categoria, Long>{

	 Optional<Categoria> findByDescripcion(String descripcion);
}
