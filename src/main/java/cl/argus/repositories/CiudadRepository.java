package cl.argus.repositories;

import cl.argus.models.Ciudad;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface CiudadRepository extends CrudRepository<Ciudad,Long>{

}