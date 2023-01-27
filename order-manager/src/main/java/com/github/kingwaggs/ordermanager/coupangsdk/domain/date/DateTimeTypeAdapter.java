package com.github.kingwaggs.ordermanager.coupangsdk.domain.date;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;

public class DateTimeTypeAdapter extends TypeAdapter<DateTime> {

    private final DateTimeFormatter parseFormatter = ISODateTimeFormat.dateOptionalTimeParser();
    private final DateTimeFormatter printFormatter = ISODateTimeFormat.dateTime();

    public DateTimeTypeAdapter() {
    }

    public void write(JsonWriter out, DateTime date) throws IOException {
        if (date == null) {
            out.nullValue();
        } else {
            out.value(this.printFormatter.print(date));
        }

    }

    public DateTime read(JsonReader in) throws IOException {
        switch(in.peek()) {
            case NULL:
                in.nextNull();
                return null;
            default:
                String date = in.nextString();
                return this.parseFormatter.parseDateTime(date);
        }
    }
}
