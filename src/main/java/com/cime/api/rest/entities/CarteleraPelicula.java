package com.cime.api.rest.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity 
@Table(name="catelera_pelicula")
public class CarteleraPelicula {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id; 
	@OneToOne
	private SalaCine salaCine; 
	private Date fechaPublicacion; 
	private Date fechaFin; 
	@ManyToOne
	private Pelicula pelicua;
	public CarteleraPelicula(Long id, SalaCine salaCine, Date fechaPublicacion, Date fechaFin, Pelicula pelicua) {
		super();
		this.id = id;
		this.salaCine = salaCine;
		this.fechaPublicacion = fechaPublicacion;
		this.fechaFin = fechaFin;
		this.pelicua = pelicua;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public SalaCine getSalaCine() {
		return salaCine;
	}
	public void setSalaCine(SalaCine salaCine) {
		this.salaCine = salaCine;
	}
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Pelicula getPelicua() {
		return pelicua;
	}
	public void setPelicua(Pelicula pelicua) {
		this.pelicua = pelicua;
	} 
	
	
	
}

