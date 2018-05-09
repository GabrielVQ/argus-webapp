package cl.argus.services;


import cl.argus.models.Container;
import cl.argus.models.Naviera;
import cl.argus.repositories.ContainerRepository;
import cl.argus.repositories.NavieraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/navieras")
public class NavieraService {

    @Autowired
    NavieraRepository navieraRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Naviera> getAllNavieras() {
        return navieraRepository.findAll();
    }
}
