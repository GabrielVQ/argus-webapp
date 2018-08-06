package cl.argus.services;


import cl.argus.models.Cobro;
import cl.argus.repositories.CobroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin (origins = "http://localhost:8080")
@RestController
@RequestMapping("/cobros")
public class CobrosService {
    @Autowired
    CobroRepository cobroRepository;


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Cobro> getAllCobros() { return cobroRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Cobro create(@RequestBody Cobro resource) { return cobroRepository.save(resource); }
}
