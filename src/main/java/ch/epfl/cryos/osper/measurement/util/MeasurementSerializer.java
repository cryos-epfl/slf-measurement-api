package ch.epfl.cryos.osper.measurement.util;

import ch.epfl.cryos.osper.measurement.model.Measurement;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by kryvych on 05/01/17.
 */
public class MeasurementSerializer extends JsonSerializer<Measurement> {


    @Override
    public void serialize(Measurement measurement, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(tz);

        jgen.writeStartArray();
        jgen.writeObject(df.format(measurement.getMeasureDate()));
        jgen.writeNumber(measurement.getValue());
        jgen.writeEndArray();

    }

}
