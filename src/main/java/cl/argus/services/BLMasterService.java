package cl.argus.services;


import cl.argus.models.BLHouse;
import cl.argus.models.BLMaster;
import cl.argus.models.Puerto;
import cl.argus.repositories.BLMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/blmasters")
public class BLMasterService {
    @Autowired
    BLMasterRepository blMasterRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<BLMaster> getAllBLMasters() {
        return blMasterRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public BLMaster create(@RequestBody BLMaster resource) {
        return blMasterRepository.save(resource);
    }
}
