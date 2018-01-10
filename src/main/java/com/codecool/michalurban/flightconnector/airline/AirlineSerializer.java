package com.codecool.michalurban.flightconnector.airline;

import com.codecool.michalurban.flightconnector.airport.Airport;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class AirlineSerializer extends StdSerializer<Airline> {

    public AirlineSerializer() {
        this(null);
    }

    public AirlineSerializer(Class<Airline> t) {
        super(t);
    }

    @Override
    public void serialize(Airline airline, JsonGenerator generator, SerializerProvider provider)
            throws IOException {

        generator.writeStartObject();
        generator.writeNumberField("id", airline.getId());
        generator.writeStringField("name", airline.getName());
        generator.writeStringField("countryOfOrigin", airline.getCountryOfOrigin());
        generator.writeArrayFieldStart("airlinesId");
        for(Airport airport: airline.getAirports()) {
            generator.writeNumber(airport.getId());
        }
        generator.writeEndArray();
        generator.writeEndObject();
    }

}