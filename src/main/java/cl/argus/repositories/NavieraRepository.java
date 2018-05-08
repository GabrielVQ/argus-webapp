package cl.argus.repositories;

import cl.argus.models.Naviera;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface NavieraRepository extends CrudRepository<Naviera,Long>{
}