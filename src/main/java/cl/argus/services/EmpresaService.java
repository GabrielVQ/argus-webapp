package cl.argus.services;


import cl.argus.models.Empresa;
import cl.argus.repositories.EmpresaRepository;
import cl.argus.repositories.PuertoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/empresas")
public class EmpresaService {

    @Autowired
    EmpresaRepository empresaRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Empresa> getAllEmpresas() {
        return empresaRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Empresa create(@RequestBody Empresa resource) {
        return empresaRepository.save(resource);
    }

    @RequestMapping(value="/nombreabrev/{nombreAbrev}", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Empresa> show(@PathVariable("nombreAbrev") String  nombreAbrev){
        //String nOperacion = Integer.parseInt(id);
        return empresaRepository.getByNombreAbrev(nombreAbrev);
    }
}
