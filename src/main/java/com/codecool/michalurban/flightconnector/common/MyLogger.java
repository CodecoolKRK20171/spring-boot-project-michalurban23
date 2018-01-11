package com.codecool.michalurban.flightconnector.common;

import com.codecool.michalurban.flightconnector.airport.AirportServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.stereotype.Component;

@Component
public class MyLogger {

    private static final Logger logger = LogManager.getLogger(AirportServiceImpl.class);
    private Marker marker = MarkerManager.getMarker("RUNTIME");

    public void logError(String message) {

        logger.error(marker, message);
    }

    public void logInfo(String message) {

        logger.info(marker, message);
    }
}
