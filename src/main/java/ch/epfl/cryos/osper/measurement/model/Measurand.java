package ch.epfl.cryos.osper.measurement.model;

import ch.epfl.cryos.osper.measurement.ApplicationFields;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

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

    @ManyToMany()
    @JoinTable(
            name = "measurand_measurand_group",
            schema = ApplicationFields.SCHEMA,
            joinColumns = @JoinColumn(name = "measurand_id "),
            inverseJoinColumns = @JoinColumn(name = "measurand_group_id")
    )
    private Set<Group> groups;

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

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
}
