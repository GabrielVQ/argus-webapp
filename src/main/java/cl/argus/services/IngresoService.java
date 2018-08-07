package cl.argus.services;


import cl.argus.models.Cobro;
import cl.argus.models.Ingreso;
import cl.argus.repositories.CobroRepository;
import cl.argus.repositories.IngresoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin (origins = "http://localhost:8080")
@RestController
@RequestMapping("/ingresos")
public class IngresoService {
    @Autowired
    IngresoRepository ingresoRepository;


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Ingreso> getAllIngresos() { return ingresoRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Ingreso create(@RequestBody Ingreso resource) { return ingresoRepository.save(resource); }

    @RequestMapping(value="/numerooperacion/{numeroOperacion}", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Ingreso> show(@PathVariable("numeroOperacion") String  numeroOperacion){
        //String nOperacion = Integer.parseInt(id);
        return ingresoRepository.getByNumeroOperacion(numeroOperacion);
    }
}
