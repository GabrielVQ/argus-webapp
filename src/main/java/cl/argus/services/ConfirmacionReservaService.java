package cl.argus.services;


import cl.argus.models.BLHouse;
import cl.argus.models.ConfirmacionReserva;
import cl.argus.repositories.BLHouseRepository;
import cl.argus.repositories.ConfirmacionReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/confirmaciones")
public class ConfirmacionReservaService {

    @Autowired
    ConfirmacionReservaRepository confirmacionReservaRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<ConfirmacionReserva> getAllConfirmaciones() {
        return confirmacionReservaRepository.findAll();
    }
}
