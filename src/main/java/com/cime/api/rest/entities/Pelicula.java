package com.cime.api.rest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="pelicula")
public class Pelicula {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id; 
	private String nombre; 
	private double duracion ; 
	@ManyToOne
	private Categoria categoria;
	private String estado; 

	public Pelicula() {
		
	}
	
	public Pelicula (String nombre, double duracion, Categoria categoria, String estado) {
		this.nombre = nombre; 
		this.duracion = duracion; 
		this.categoria = categoria;
		this.estado = estado; 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getDuracion() {
		return duracion;
	}

	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
}
