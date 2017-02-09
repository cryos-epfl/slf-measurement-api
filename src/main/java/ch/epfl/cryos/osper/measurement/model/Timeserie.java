package ch.epfl.cryos.osper.measurement.model;

import ch.epfl.cryos.osper.measurement.ApplicationFields;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

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

    @Column(name = "device_code")
    private String deviceCode;

    @ManyToOne
    @JoinColumn(name = "measurand_id")
    @JsonUnwrapped
    private Measurand measurand;

    @Column(name = "seq_nr")
    private int sequenceNumber;

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

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
}
