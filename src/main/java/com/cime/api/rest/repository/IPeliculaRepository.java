package com.cime.api.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cime.api.rest.entities.Pelicula;

public interface IPeliculaRepository extends JpaRepository<Pelicula, Long> {
	List<Pelicula> findByCategoriaDescripcion(String descripcion);

}
