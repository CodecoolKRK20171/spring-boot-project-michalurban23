package com.codecool.michalurban.flightconnector.airport;

import com.codecool.michalurban.flightconnector.common.ModelPatcher;
import com.codecool.michalurban.flightconnector.exception.EntityNotFoundException;
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

        if (repository.exists(id)) {
            return repository.findOne(id);
        } else {
            throw new EntityNotFoundException("No object with this ID");
        }
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
    public Airport patch(Integer id, Airport patchingAirport) {

        Airport oldAirport = repository.findOne(id);

        if (oldAirport == null) {
            throw new EntityNotFoundException("No resource with such id");
        } else {
            ModelPatcher patcher = new AirportPatcher(oldAirport, patchingAirport);
            patcher.applyPatch();
            return repository.save((Airport) patcher.getUpdatedObject());
        }
    }

}
