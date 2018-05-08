package cl.argus.repositories;

import cl.argus.models.Ciudad;
import cl.argus.models.Puerto;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface PuertoRepository extends CrudRepository<Puerto,Long>{
}