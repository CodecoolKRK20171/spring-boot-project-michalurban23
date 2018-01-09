package com.codecool.michalurban.flightconnector.airline;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/airlines")
public class AirlineController {

    private AirlineService service;

    public AirlineController(AirlineService service) {

        this.service = service;
    }

    @GetMapping(path = "")
    public Iterable<Airline> index() {

        return service.findAll();
    }

    @GetMapping(path = "/{id}")
    public Airline show(@PathVariable Integer id) {

        return service.find(id);
    }

    @PostMapping(path = "")
    public Airline create(@RequestBody Airline airline) {

        service.save(airline);
        return airline;
    }

    @DeleteMapping(path = "/{id}")
    public Iterable<Airline> remove(@PathVariable Integer id) {

        service.remove(id);
        return index();
    }

    @PatchMapping(path = "/{id}")
    public Airline patch(@PathVariable Integer id, @RequestBody Airline airline) {

        return service.patch(id, airline); // TODO
    }

}
