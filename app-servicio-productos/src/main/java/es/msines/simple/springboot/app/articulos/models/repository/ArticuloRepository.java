package es.msines.simple.springboot.app.articulos.models.repository;

import org.springframework.data.repository.CrudRepository;

import es.msines.simple.springboot.app.commons.models.entity.Articulo;

public interface ArticuloRepository extends CrudRepository<Articulo, Long>{

}
