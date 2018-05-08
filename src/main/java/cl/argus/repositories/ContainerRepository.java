package cl.argus.repositories;

import org.springframework.data.repository.CrudRepository;
import cl.argus.models.Container;
import javax.transaction.Transactional;
import java.awt.*;

@Transactional
public interface ContainerRepository extends CrudRepository<Container,Long>{
}