package ch.epfl.cryos.osper.model;

import ch.epfl.cryos.osper.ApplicationFields;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by kryvych on 23/12/16.
 */
@Entity
@Table(name = "v_timeserie", schema = ApplicationFields.SCHEMA)
public class Timeserie {

    @Id
    @NotNull
    private Long id;

    @Column(name = "station_id")
    @NotNull
    private Long stationId;

    @Column(name = "device_id")
    @NotNull
    private Long deviceId;

    @ManyToOne
    @JoinColumn(name = "measurand_id")
    private Measurand measurand;

    private Date since;

    private Date until;

    private Timeserie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Measurand getMeasurand() {
        return measurand;
    }

    public void setMeasurand(Measurand measurand) {
        this.measurand = measurand;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public Date getUntil() {
        return until;
    }

    public void setUntil(Date until) {

        this.until = until;
    }
}
