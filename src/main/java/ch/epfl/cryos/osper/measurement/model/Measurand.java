package ch.epfl.cryos.osper.measurement.model;

import ch.epfl.cryos.osper.measurement.ApplicationFields;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by kryvych on 29/12/16.
 */
@Entity
@Table(name = "measurand", schema = ApplicationFields.SCHEMA)
public class Measurand {

    @Id
    @NotNull
    private Long id;

    private String code;

    private String description;

    @Column(name = "aggregation_interval")
    private String aggregationInterval;

    @Column(name = "aggregation_type")
    private String aggregationType;

    private String unit;

    private Measurand() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAggregationInterval() {
        return aggregationInterval;
    }

    public void setAggregationInterval(String aggregationInterval) {
        this.aggregationInterval = aggregationInterval;
    }

    public String getAggregationType() {
        return aggregationType;
    }

    public void setAggregationType(String aggregationType) {
        this.aggregationType = aggregationType;
    }

    public String getUnit() {

        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
