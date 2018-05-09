package cl.argus.services;


import cl.argus.models.Naviera;
import cl.argus.models.Puerto;
import cl.argus.repositories.NavieraRepository;
import cl.argus.repositories.PuertoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/puertos")
public class PuertoService {

    @Autowired
    PuertoRepository puertoRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Puerto> getAllPuertos() {
        return puertoRepository.findAll();
    }
}
