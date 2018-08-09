package cl.argus.repositories;

import cl.argus.models.BLHouse;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface BLHouseRepository extends CrudRepository<BLHouse,Long>{

    BLHouse getById(Long id);
    List <BLHouse> getByNumeroOperacion(String numeroOperacion);
}