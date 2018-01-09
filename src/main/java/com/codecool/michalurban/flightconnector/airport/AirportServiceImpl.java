package com.codecool.michalurban.flightconnector.airport;

import org.springframework.stereotype.Service;

@Service
public class AirportServiceImpl implements AirportService {

    private AirportRepository repository;

    public AirportServiceImpl(AirportRepository repository) {

        this.repository = repository;
    }

    @Override
    public Iterable<Airport> findAll() {

        return repository.findAll();
    }

    @Override
    public Airport find(Integer id) {

        return repository.findOne(id);
    }

    @Override
    public void save(Airport airport) {

        repository.save(airport);
    }

    @Override
    public void remove(Integer id) {

        repository.delete(id);
    }

    @Override
    public Airport patch(Integer id, Airport airport) {

        return null; // TODO
    }

}
