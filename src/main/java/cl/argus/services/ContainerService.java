package cl.argus.services;


import cl.argus.models.ConfirmacionReserva;
import cl.argus.models.Container;
import cl.argus.repositories.ConfirmacionReservaRepository;
import cl.argus.repositories.ContainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/containers")
public class ContainerService {

    @Autowired
    ContainerRepository containerRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Container> getAllContainers() {
        return containerRepository.findAll();
    }
}
