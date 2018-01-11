package com.codecool.michalurban.flightconnector.airline;

import com.codecool.michalurban.flightconnector.common.ModelPatcher;
import com.codecool.michalurban.flightconnector.common.MyLogger;
import com.codecool.michalurban.flightconnector.exception.EntityNotFoundException;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.HashSet;

@Service
public class AirlineServiceImpl implements AirlineService {

    private AirlineRepository repository;
    private MyLogger logger;

    public AirlineServiceImpl(AirlineRepository repository, MyLogger logger) {

        this.repository = repository;
        this.logger = logger;
    }

    @Override
    public Iterable<Airline> findAll() {

        logger.logInfo("Listed all airlines.");
        return repository.findAllByArchivedIsFalse();
    }

    @Override
    public Airline find(Integer id) {

        if (repository.exists(id)) {
            logger.logInfo("Listed airline id{" + id + "}.");
            return repository.findAirlineByIdAndArchivedIsFalse(id);
        } else {
            logger.logError("Request airline id{" + id + "}. Object not found.");
            throw new EntityNotFoundException("No object with this ID");
        }
    }

    @Override
    public void save(Airline airline) {

        try {
            repository.save(airline);
            logger.logInfo(String.format("Created new airline with name: %s, country: %s",
                    airline.getName(), airline.getCountryOfOrigin()));
        } catch (ConstraintViolationException e) {
            logger.logError("Failed to create new Airline");
            throw new ConstraintViolationException("Unique constraints failed on one or more fields",
                    new HashSet<>());
        }
    }

    @Override
    public void remove(Integer id) {

        logger.logInfo("Archived airline id{" + id + "}.");
        repository.findOne(id).setArchived(true);
    }

    @Override
    public Airline patch(Integer id, Airline patchingAirline) {

        Airline oldAirline = repository.findAirlineByIdAndArchivedIsFalse(id);

        if (oldAirline == null) {
            logger.logError("Request patching airline id{" + id + "}. Object not found.");
            throw new EntityNotFoundException("No resource with such id");
        } else {
            ModelPatcher patcher = new AirlinePatcher(oldAirline, patchingAirline);
            patcher.applyPatch();
            logger.logInfo("Patched airline id{" + id + "}.");
            return repository.save((Airline) patcher.getUpdatedObject());
        }
    }

}
