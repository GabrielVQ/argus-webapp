package cl.argus.services;


import cl.argus.models.BLHouse;
import cl.argus.models.Ciudad;
import cl.argus.repositories.BLHouseRepository;
import cl.argus.repositories.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/blhouses")
public class BLHouseService {
    @Autowired
    BLHouseRepository blHouseRepository;


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<BLHouse> getAllHouses() {
        return blHouseRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public BLHouse create(@RequestBody BLHouse resource) {
        return blHouseRepository.save(resource);
    }


}
