package cl.argus.repositories;

import cl.argus.models.ConfirmacionReserva;
import cl.argus.models.Moneda;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface MonedaRepository extends CrudRepository<Moneda,Long>{
}