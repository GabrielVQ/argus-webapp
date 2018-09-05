package cl.argus.services;


import cl.argus.models.Cargament;
import cl.argus.models.Naviera;
import cl.argus.repositories.CargamentRepository;
import cl.argus.repositories.NavieraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/cargaments")
public class CargamentService {

    @Autowired
    CargamentRepository cargamentRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Cargament> getAllCargaments() {
        return cargamentRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Cargament create(@RequestBody Cargament resource) { return cargamentRepository.save(resource); }


    @RequestMapping(value="/numerooperacion/{numeroOperacion}", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Cargament> show(@PathVariable("numeroOperacion") String  numeroOperacion){
        //String nOperacion = Integer.parseInt(id);
        return cargamentRepository.getByNumeroOperacion(numeroOperacion);
    }

    @RequestMapping(value = "/cargamentBorrar/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") Long id) {
                cargamentRepository.delete(id);

    }
}
