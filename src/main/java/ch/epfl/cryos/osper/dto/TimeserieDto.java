package ch.epfl.cryos.osper.dto;

import ch.epfl.cryos.osper.model.Measurement;
import ch.epfl.cryos.osper.model.Timeserie;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Created by kryvych on 19/10/16.
 */
public class TimeserieDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Timeserie info;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Measurement> measurements;

    public TimeserieDto(Timeserie info, List<Measurement> measurements) {
        this.info = info;
        this.measurements = measurements;
    }

    public Timeserie getInfo() {
        return info;
    }

    public void setInfo(Timeserie info) {
        this.info = info;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }
}

