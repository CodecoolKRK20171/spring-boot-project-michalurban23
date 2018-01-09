package com.codecool.michalurban.flightconnector.airline;

import com.codecool.michalurban.flightconnector.common.ModelPatcher;

public class AirlinePatcher implements ModelPatcher{

    private Airline originalAirline;
    private Airline patcherAirline;

    @Override
    public void applyPatch() {

        if (patcherAirline.getName() != null) {
            originalAirline.setName(patcherAirline.getName());
        }
        if (patcherAirline.getCountryOfOrigin() != null) {
            originalAirline.setCountryOfOrigin(patcherAirline.getCountryOfOrigin());
        }
        if (patcherAirline.getAirports() != null) {
            originalAirline.setAirports(patcherAirline.getAirports());
        }
    }

    public AirlinePatcher(Airline originalAirline, Airline patcherAirline) {

        this.originalAirline = originalAirline;
        this.patcherAirline = patcherAirline;
    }

    public Airline getUpdatedObject() {

        return originalAirline;
    }

}
