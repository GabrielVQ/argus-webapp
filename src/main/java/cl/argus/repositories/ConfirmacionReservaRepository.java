package cl.argus.repositories;

import cl.argus.models.ConfirmacionReserva;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface ConfirmacionReservaRepository extends CrudRepository<ConfirmacionReserva,Long>{
}