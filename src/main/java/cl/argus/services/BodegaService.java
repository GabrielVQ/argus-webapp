package cl.argus.services;


import cl.argus.models.Bodega;
import cl.argus.models.Ciudad;
import cl.argus.repositories.BodegaRepository;
import cl.argus.repositories.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin (origins = "http://localhost:8080")
@RestController
@RequestMapping("/bodegas")
public class BodegaService {
    @Autowired
    BodegaRepository bodegaRepository;


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Bodega> getAllBodegas() {
        return bodegaRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Bodega create(@RequestBody Bodega resource) {
        return bodegaRepository.save(resource);
    }
}
