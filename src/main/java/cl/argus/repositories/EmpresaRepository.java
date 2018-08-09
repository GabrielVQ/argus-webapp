package cl.argus.repositories;

import cl.argus.models.Empresa;
import cl.argus.models.Naviera;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface EmpresaRepository extends CrudRepository<Empresa,Long>{

    List <Empresa> getByNombreAbrev(String nombreAbrev);
}