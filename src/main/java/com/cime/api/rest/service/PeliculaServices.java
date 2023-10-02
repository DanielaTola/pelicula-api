package com.cime.api.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cime.api.rest.entities.Categoria;
import com.cime.api.rest.entities.Pelicula;
import com.cime.api.rest.exceptions.MiExcepcion;
import com.cime.api.rest.repository.ICategoria;
import com.cime.api.rest.repository.IPeliculaRepository;

import jakarta.transaction.Transactional;

@Service
public class PeliculaServices {

	@Autowired
	private final IPeliculaRepository peliculaRepository;
	@Autowired
	private final ICategoria categoriaRepository;

	public PeliculaServices(IPeliculaRepository peliculaRepository, ICategoria categoriaRepository) {
		super();
		this.peliculaRepository = peliculaRepository;
		this.categoriaRepository = categoriaRepository;
	}

	@Transactional
	public Pelicula registrarPelicula(Pelicula peliculaNueva) throws MiExcepcion {
	
		Categoria categoria = peliculaNueva.getCategoria();
		Optional<Categoria> categoriaExistente = categoriaRepository.findByDescripcion(categoria.getDescripcion());

		if (categoriaExistente.isPresent()) {
			peliculaNueva.setCategoria(categoriaExistente.get());
		} else {
			categoria = categoriaRepository.save(categoria);
			peliculaNueva.setCategoria(categoria);
		}

		return peliculaRepository.save(peliculaNueva);
	}
	
	public List<Categoria> todasLasCategorias()throws MiExcepcion {
		List<Categoria> categorias = categoriaRepository.findAll();

		if (categorias.isEmpty()) {
			throw new MiExcepcion("No lista de categorias", 6, "Sin resultados");
		}
		return categorias;
	}

	public Pelicula buscarPeliculaPorId(Long id) throws MiExcepcion {
		Optional<Pelicula> peliculaOptional = peliculaRepository.findById(id);

		if (peliculaOptional.isPresent()) {
			return peliculaOptional.get();
		} else {
			throw new MiExcepcion("Ha ocurrido un error.", 1, "No se encontro registro");
		}
	}

	public List<Pelicula> todasLasPeliculas() throws MiExcepcion {
		List<Pelicula> peliculas = peliculaRepository.findAll();

		if (peliculas.isEmpty()) {
			throw new MiExcepcion("No existen peliculas registradas", 5, "Sin resultados");
		}
		return peliculas;
	}

	public List<Pelicula> buscarPeliculasPorDescripcionCategoria(String descripcionCategoria) throws MiExcepcion {
		List<Pelicula> peliculas = peliculaRepository.findByCategoriaDescripcion(descripcionCategoria);

		if (peliculas.isEmpty()) {
			throw new MiExcepcion("No se encontraron películas para la descripción de categoría proporcionada.", 2,
					"Sin resultados");
		}

		return peliculas;
	}
	
	public void eliminarPelicula(Long peliculaId) throws MiExcepcion{
        Optional<Pelicula> peliculaOptional = peliculaRepository.findById(peliculaId);
        
        if (peliculaOptional.isPresent()) {
            Pelicula pelicula = peliculaOptional.get();
            pelicula.setEstado("I"); // Cambiar el estado a inactivo
            peliculaRepository.save(pelicula);
        }else {
        	throw new MiExcepcion("No se encontraron películas para la descripción de categoría proporcionada.", 2,
					"Sin resultados");
        }
    }
	
	public Pelicula actualizarPelicula(Long id, Pelicula peliculaActualizada) throws MiExcepcion {
	    Optional<Pelicula> peliculaOptional = peliculaRepository.findById(id);
	    if (peliculaOptional.isPresent()) {
	        Pelicula pelicula = peliculaOptional.get();
	        
	        Categoria categoriaPersistente = categoriaRepository.findById(peliculaActualizada.getCategoria().getId())
	                .orElseThrow(() -> new MiExcepcion("La categoría no existe.", 2, "Categoría no encontrada"));

	        pelicula.setNombre(peliculaActualizada.getNombre());
	        pelicula.setDuracion(peliculaActualizada.getDuracion());
	        pelicula.setCategoria(categoriaPersistente);
	        pelicula.setEstado(peliculaActualizada.getEstado());
	        
	        return peliculaRepository.save(pelicula);
	    } else {
	        throw new MiExcepcion("No se encontraron películas para el ID proporcionado.", 2, "Sin resultados");
	    }
	}


}
