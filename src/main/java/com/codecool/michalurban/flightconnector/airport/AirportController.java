package com.codecool.michalurban.flightconnector.airport;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/airports")
public class AirportController {

    private AirportService service;

    public AirportController(AirportService service) {

        this.service = service;
    }

    @GetMapping(path = "")
    public Iterable<Airport> index() {

        return service.findAll();
    }

    @GetMapping(path = "/{id}")
    public Airport show(@PathVariable Integer id) {

        return service.find(id);
    }

    @PostMapping(path = "")
    public Airport create(@RequestBody Airport airport) {

        service.save(airport);
        return airport;
    }

    @DeleteMapping(path = "/{id}")
    public Iterable<Airport> remove(@PathVariable Integer id) {

        service.remove(id);
        return index();
    }

    @PatchMapping(path = "/{id}")
    public Airport patch(@PathVariable Integer id, @RequestBody Airport airport) {

        return service.patch(id, airport);
    }

}
