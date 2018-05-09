package cl.argus.services;


import cl.argus.models.Moneda;
import cl.argus.models.Puerto;
import cl.argus.repositories.MonedaRepository;
import cl.argus.repositories.PuertoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/monedas")
public class MonedaService {

    @Autowired
    MonedaRepository monedaRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Moneda> getAllPuertos() {
        return monedaRepository.findAll();
    }
}
