package cl.argus.services;


import cl.argus.models.Nave;
import cl.argus.models.Puerto;
import cl.argus.repositories.NaveRepository;
import cl.argus.repositories.PuertoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/naves")
public class NaveService {

    @Autowired
    NaveRepository naveRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Nave> getAllPuertos() {
        return naveRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Nave create(@RequestBody Nave resource) {
        return naveRepository.save(resource);
    }
}
