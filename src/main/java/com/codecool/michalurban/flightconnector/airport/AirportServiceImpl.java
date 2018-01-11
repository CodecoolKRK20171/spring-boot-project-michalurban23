package com.codecool.michalurban.flightconnector.airport;

import com.codecool.michalurban.flightconnector.common.ModelPatcher;
import com.codecool.michalurban.flightconnector.common.MyLogger;
import com.codecool.michalurban.flightconnector.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.HashSet;

@Service
public class AirportServiceImpl implements AirportService {

    private AirportRepository repository;
    private MyLogger logger;

    public AirportServiceImpl(AirportRepository repository, MyLogger logger) {

        this.repository = repository;
        this.logger = logger;
    }

    @Override
    public Iterable<Airport> findAll() {

        logger.logInfo("Listed all airports.");
        return repository.findAllByArchivedIsFalse();
    }

    @Override
    public Airport find(Integer id) {

        if (repository.exists(id)) {
            logger.logInfo("Listed airport id{" + id + "}.");
            return repository.findAirportByIdAndArchivedIsFalse(id);
        } else {
            logger.logError("Request airport id{" + id + "}. Object not found.");
            throw new EntityNotFoundException("No object with this ID");
        }
    }

    @Override
    public void save(Airport airport) {

        try {
            repository.save(airport);
            logger.logInfo(String.format("Created new airport with name: %s, country: %s",
                                        airport.getShortName(), airport.getCountry()));
        } catch (ConstraintViolationException e) {
            logger.logError("Failed to create new Airport");
            throw new ConstraintViolationException("Unique constraints failed on one or more fields",
                    new HashSet<>());
        }
    }

    @Override
    public void remove(Integer id) {

        logger.logInfo("Archived airport id{" + id + "}.");
        repository.findOne(id).setArchived(true);
    }

    @Override
    public Airport patch(Integer id, Airport patchingAirport) {

        Airport oldAirport = repository.findAirportByIdAndArchivedIsFalse(id);

        if (oldAirport == null) {
            logger.logError("Request patching airport id{" + id + "}. Object not found.");
            throw new EntityNotFoundException("No resource with such id");
        } else {
            ModelPatcher patcher = new AirportPatcher(oldAirport, patchingAirport);
            patcher.applyPatch();
            logger.logInfo("Patched airport id{" + id + "}.");
            return repository.save((Airport) patcher.getUpdatedObject());
        }
    }

}
