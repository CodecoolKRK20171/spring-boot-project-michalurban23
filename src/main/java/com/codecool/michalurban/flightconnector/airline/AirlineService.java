package com.codecool.michalurban.flightconnector.airline;

public interface AirlineService {

    Iterable<Airline> findAll();
    Airline find(Integer id);
    void save(Airline airline);
    void remove(Integer id);
    Airline patch(Integer id, Airline airline);
}
