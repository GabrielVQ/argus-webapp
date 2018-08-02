package cl.argus.repositories;

import cl.argus.models.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<User,Long>{
    User findByEmail(String email);
    User findByRut(String rut);
    User findByEmailAndPassword(String correo, String pass);
}