package com.codecool.michalurban.flightconnector.airline;

import org.springframework.stereotype.Service;

@Service
public class AirlineServiceImpl implements AirlineService {

    private AirlineRepository repository;

    public AirlineServiceImpl(AirlineRepository repository) {

        this.repository = repository;
    }

    @Override
    public Iterable<Airline> findAll() {

        return repository.findAll();
    }

    @Override
    public Airline find(Integer id) {

        return repository.findOne(id);
    }

    @Override
    public void save(Airline airline) {

        repository.save(airline);
    }

    @Override
    public void remove(Integer id) {

        repository.delete(id);
    }

    @Override
    public Airline patch(Integer id, Airline airline) {

        return null; // TODO
    }


}
