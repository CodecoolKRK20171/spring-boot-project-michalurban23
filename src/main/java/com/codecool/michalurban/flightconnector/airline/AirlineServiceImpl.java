package com.codecool.michalurban.flightconnector.airline;

import com.codecool.michalurban.flightconnector.common.ModelPatcher;
import com.codecool.michalurban.flightconnector.exception.EntityNotFoundException;
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

        if (repository.exists(id)) {
            return repository.findOne(id);
        } else {
            throw new EntityNotFoundException("No object with this ID");
        }
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
    public Airline patch(Integer id, Airline patchingAirline) {

        Airline oldAirline = repository.findOne(id);

        if (oldAirline == null) {
            throw new EntityNotFoundException("No resource with such id");
        } else {
            ModelPatcher patcher = new AirlinePatcher(oldAirline, patchingAirline);
            patcher.applyPatch();
            return repository.save((Airline) patcher.getUpdatedObject());
        }
    }

}
