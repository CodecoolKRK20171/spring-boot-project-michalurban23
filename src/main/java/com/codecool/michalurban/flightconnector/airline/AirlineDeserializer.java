package com.codecool.michalurban.flightconnector.airline;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AirlineDeserializer extends StdDeserializer<Airline> {

    @Autowired
    private SessionFactory hibernateFactory;

    public AirlineDeserializer() {
        this(null);
    }

    public AirlineDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Airline deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {

        JsonNode node = jp.getCodec().readTree(jp);

        JsonNode name = node.get("name");
        JsonNode country = node.get("countryOfOrigin");
        JsonNode airportsIds = node.get("airports");

        Airline airline = new Airline();

        if (airportsIds != null) {
            if (airportsIds.isArray()) {
                for (JsonNode id : airportsIds) {
                    Session session;
                    try {
                        session = hibernateFactory.getCurrentSession();
                    } catch (HibernateException e) {
                        session = hibernateFactory.openSession();
                    }
                    session.beginTransaction();

                    String values = id + "," + id; // TODO
                    String query = "INSERT INTO airport_airline(airport_id, airline_id) VALUES(" + values + ");";

                    SQLQuery sqlQuery = session.createSQLQuery(query);
                    sqlQuery.executeUpdate();
                    session.getTransaction().commit();
                }
            }
        }

        airline.setName(name == null ? null : name.asText());
        airline.setCountryOfOrigin(country == null ? null : country.asText());

        return airline;
    }

}