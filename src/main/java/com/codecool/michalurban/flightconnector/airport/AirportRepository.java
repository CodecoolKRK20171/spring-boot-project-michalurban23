package com.codecool.michalurban.flightconnector.airport;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@javax.transaction.Transactional
public interface AirportRepository extends CrudRepository<Airport, Integer> {

    Iterable<Airport> findAllByArchivedIsFalse();
    Airport findAirportByIdAndArchivedIsFalse(Integer id);
}
