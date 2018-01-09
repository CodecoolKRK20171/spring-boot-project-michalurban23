package com.codecool.michalurban.flightconnector.airport;

import com.codecool.michalurban.flightconnector.common.ModelPatcher;

public class AirportPatcher implements ModelPatcher {

    private Airport originalAirport;
    private Airport patcherAirport;

    @Override
    public void applyPatch() {

        if (patcherAirport.getShortName() != null) {
            originalAirport.setShortName(patcherAirport.getShortName());
        }
        if (patcherAirport.getLongName() != null) {
            originalAirport.setLongName(patcherAirport.getLongName());
        }
        if (patcherAirport.getCountry() != null) {
            originalAirport.setCountry(patcherAirport.getCountry());
        }
        if (patcherAirport.getAirlines() != null) {
            originalAirport.setAirlines(patcherAirport.getAirlines());
        }
    }

    public AirportPatcher(Airport originalAirport, Airport patcherAirport) {

        this.originalAirport = originalAirport;
        this.patcherAirport = patcherAirport;
    }

    public Airport getUpdatedObject() {

        return originalAirport;
    }

}
