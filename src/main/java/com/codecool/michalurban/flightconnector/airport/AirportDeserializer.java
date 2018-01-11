package com.codecool.michalurban.flightconnector.airport;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AirportDeserializer extends StdDeserializer<Airport> {

    @Autowired
    private SessionFactory hibernateFactory;

    public AirportDeserializer() {
        this(null);
    }

    public AirportDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Airport deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {

        JsonNode node = jp.getCodec().readTree(jp);

        JsonNode name = node.get("shortName");
        JsonNode longName = node.get("longName");
        JsonNode country = node.get("country");
        JsonNode airlinesIds = node.get("airlines");

        Airport airport = new Airport();

        if (airlinesIds != null) {
            if (airlinesIds.isArray()) {
                for (JsonNode id : airlinesIds) {
                    Session session;
                    try {
                        session = hibernateFactory.getCurrentSession();
                    } catch (HibernateException e) {
                        session = hibernateFactory.openSession();
                    }
                    session.beginTransaction();
                    // String values = id + "," + airline.getId();
                    String values = 1 + "," + 1; // TODO
                    String query = "INSERT INTO airport_airline(airport_id, airline_id) VALUES(" + values + ");";
                    SQLQuery sqlQuery = session.createSQLQuery(query);
                    sqlQuery.executeUpdate();
                    session.getTransaction().commit();
                }
            }
        }

        airport.setShortName(name == null ? null : name.asText());
        airport.setCountry(country == null ? null : country.asText());
        airport.setLongName(longName == null ? null : longName.asText());

        return airport;
    }

}