package cl.argus.services;


import cl.argus.models.Empresa;
import cl.argus.models.Puerto;
import cl.argus.repositories.EmpresaRepository;
import cl.argus.repositories.PuertoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
