package cl.argus.repositories;

import cl.argus.models.BLHouse;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface BLHouseRepository extends CrudRepository<BLHouse,Long>{
}