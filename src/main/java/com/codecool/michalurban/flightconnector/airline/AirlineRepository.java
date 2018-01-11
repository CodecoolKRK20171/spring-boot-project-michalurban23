package com.codecool.michalurban.flightconnector.airline;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@javax.transaction.Transactional
public interface AirlineRepository extends CrudRepository<Airline, Integer> {

    Iterable<Airline> findAllByArchivedIsFalse();
    Airline findAirlineByIdAndArchivedIsFalse(Integer id);
}
