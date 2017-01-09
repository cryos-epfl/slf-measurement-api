package ch.epfl.cryos.osper.measurement.util;

import ch.epfl.cryos.osper.measurement.model.Measurement;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by kryvych on 05/01/17.
 */
public class MeasurementSerializer extends JsonSerializer<Measurement> {


    @Override
    public void serialize(Measurement measurement, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartArray();
        jgen.writeObject(measurement.getMeasureDate());
        jgen.writeNumber(measurement.getValue());
        jgen.writeEndArray();

    }

}
