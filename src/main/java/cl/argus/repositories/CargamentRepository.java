package cl.argus.repositories;

import cl.argus.models.Cargament;
import cl.argus.models.Naviera;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface CargamentRepository extends CrudRepository<Cargament,Long>{
}