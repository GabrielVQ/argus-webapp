package cl.argus.services;


import cl.argus.models.BLMaster;
import cl.argus.models.Ciudad;
import cl.argus.repositories.BLMasterRepository;
import cl.argus.repositories.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/ciudades")
public class CiudadService {
    @Autowired
    CiudadRepository ciudadRepository;


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Ciudad> getAllCiudades() {
        return ciudadRepository.findAll();
    }
}
