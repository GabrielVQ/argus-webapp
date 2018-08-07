package cl.argus.repositories;

import cl.argus.models.Cobro;
import cl.argus.models.Ingreso;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface IngresoRepository extends CrudRepository<Ingreso,Long>{

    List<Ingreso> getByNumeroOperacion(String numeroOperacion);
}