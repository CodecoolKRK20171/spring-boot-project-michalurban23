package com.codecool.michalurban.flightconnector.airport;

public interface AirportService {

    Iterable<Airport> findAll();
    Airport find(Integer id);
    void save(Airport airport);
    void remove(Integer id);
    Airport patch(Integer id, Airport airport);
}
