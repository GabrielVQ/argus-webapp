package cl.argus.repositories;

import cl.argus.models.Cobro;
import cl.argus.models.Container;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface CobroRepository extends CrudRepository<Cobro,Long>{

}