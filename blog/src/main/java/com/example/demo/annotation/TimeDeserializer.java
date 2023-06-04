package com.example.demo.annotation;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeDeserializer extends JsonDeserializer<Time> {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("H:mm");

    @Override
    public Time deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String timeString = jsonParser.getText();
        LocalTime localTime = LocalTime.parse(timeString, TIME_FORMATTER);
        return Time.valueOf(localTime);
    }
}
