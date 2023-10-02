package com.cime.api.rest.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cime.api.rest.entities.Categoria;
import com.cime.api.rest.entities.Pelicula;
import com.cime.api.rest.exceptions.MiExcepcion;
import com.cime.api.rest.service.PeliculaServices;

@RestController
@RequestMapping("/api/pelicula")
public class PeliculaController {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(PeliculaController.class);

	@Autowired
	PeliculaServices peliculaServices;

	@PostMapping("/")
	public ResponseEntity<?> registrarPelicula(@RequestBody Pelicula pelicula) {
		try {
			Pelicula nuevaPelicula = peliculaServices.registrarPelicula(pelicula);
			return new ResponseEntity<>(nuevaPelicula, HttpStatus.CREATED);
		} catch (MiExcepcion e) {
			log.info("Excepción al registar una pelicula: {} - {} - {}", e.getCodigo(), e.getMessage(),
					e.getDescripcion());
			return new ResponseEntity<>("Error al registrar la película: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/todaslaspeliculas")
	public ResponseEntity<List<Pelicula>> todasLasPeliculas(){
		try {
			List<Pelicula> peliculas = peliculaServices.todasLasPeliculas();
			if (!peliculas.isEmpty()) {
				return new ResponseEntity<>(peliculas, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (MiExcepcion e) {
			((org.slf4j.Logger) log).info("Entro en excepcion " + e.getMessage(), e.getCodigo(), e.getDescripcion());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/todaslascategorias")
	public ResponseEntity<List<Categoria>> todasLasCaregorias(){
		try {
			List<Categoria> categorias = peliculaServices.todasLasCategorias();
			if (!categorias.isEmpty()) {
				return new ResponseEntity<>(categorias, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (MiExcepcion e) {
			((org.slf4j.Logger) log).info("Entro en excepcion " + e.getMessage(), e.getCodigo(), e.getDescripcion());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@GetMapping("/{id}")
	public ResponseEntity<Pelicula> buscarPeliculaPorId(@PathVariable Long id) {
		Pelicula pelicula;
		try {
			pelicula = peliculaServices.buscarPeliculaPorId(id);
			if (pelicula != null) {
				return new ResponseEntity<>(pelicula, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (MiExcepcion e) {

			log.info("Excepción en buscarPeliculaPorId: {} - {} - {}", e.getCodigo(), e.getMessage(),
					e.getDescripcion());

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/buscarPorDescripcionCategoria/{descripcionCategoria}")
	public ResponseEntity<List<Pelicula>> buscarPeliculasPorDescripcionCategoria(
			@PathVariable String descripcionCategoria) {
		try {
			List<Pelicula> peliculas = peliculaServices.buscarPeliculasPorDescripcionCategoria(descripcionCategoria);
			if (!peliculas.isEmpty()) {
				return new ResponseEntity<>(peliculas, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (MiExcepcion e) {
			((org.slf4j.Logger) log).info("Entro en excepcion " + e.getMessage(), e.getCodigo(), e.getDescripcion());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/eliminarPelicula/{peliculaId}")
    public ResponseEntity<String> eliminarPelicula(@PathVariable Long peliculaId) {
        try {
        	peliculaServices.eliminarPelicula(peliculaId);
            return ResponseEntity.ok("Película eliminada con éxito.");
        } catch (MiExcepcion e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la película: " + e.getMessage());
        }
    }

	@PutMapping("/actualizarPelicula/{peliculaId}")
	public ResponseEntity<Pelicula> actualizarPelicula(@PathVariable Long peliculaId, @RequestBody Pelicula peliculaActualizada) {
	    try {
	        Pelicula peliculaActualizadaEnBaseDeDatos = peliculaServices.actualizarPelicula(peliculaId, peliculaActualizada);

	        if (peliculaActualizadaEnBaseDeDatos != null) {
	            return new ResponseEntity<>(peliculaActualizadaEnBaseDeDatos, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    } catch (MiExcepcion e) {
	        log.error("Excepción en actualizarPelicula: {} - {} - {}", e.getCodigo(), e.getMessage(), e.getDescripcion());
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

}
