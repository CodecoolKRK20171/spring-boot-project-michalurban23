package com.codecool.michalurban.flightconnector.airport;

import com.codecool.michalurban.flightconnector.airline.Airline;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class AirportSerializer extends StdSerializer<Airport> {

    public AirportSerializer() {
        this(null);
    }

    public AirportSerializer(Class<Airport> t) {
        super(t);
    }

    @Override
    public void serialize(Airport airport, JsonGenerator generator, SerializerProvider provider)
            throws IOException {

        generator.writeStartObject();
        generator.writeNumberField("id", airport.getId());
        generator.writeStringField("country", airport.getCountry());
        generator.writeStringField("shortName", airport.getShortName());
        generator.writeStringField("longName", airport.getLongName());
        generator.writeArrayFieldStart("airlines");
        Iterable<Airline> airlines = airport.getAirlines();
        if (airlines != null) {
            for(Airline airline: airlines) {
                generator.writeNumber(airline.getId());
            }
        }
        generator.writeEndArray();
        generator.writeEndObject();
    }

}