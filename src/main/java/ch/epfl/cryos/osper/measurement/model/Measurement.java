package ch.epfl.cryos.osper.measurement.model;

import ch.epfl.cryos.osper.measurement.ApplicationFields;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by kryvych on 05/01/17.
 */
@Entity
@Table(name = "v_measurement", schema = ApplicationFields.SCHEMA)
public class Measurement {

    @Id
    @NotNull
    private Long id;

    @Column(name = "timeserie_id")
    private Long timeserieId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="measure_date")
    private Date measureDate;

    private BigDecimal value;

    public Measurement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getMeasureDate() {
        return measureDate;
    }

    public void setMeasureDate(Date measureDate) {
        this.measureDate = measureDate;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
