package ch.epfl.cryos.osper.measurement.model;

import ch.epfl.cryos.osper.measurement.ApplicationFields;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by kryvych on 09/01/17.
 */
@Entity
@Table(name = "measurand_group", schema = ApplicationFields.SCHEMA)
public class Group {

    @Id
    @NotNull
    private Long id;
    private String code;
    private String description;

    public Group() {
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
}
