package cl.argus.repositories;

import cl.argus.models.Cobro;
import cl.argus.models.Ingreso;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface IngresoRepository extends CrudRepository<Ingreso,Long>{

}