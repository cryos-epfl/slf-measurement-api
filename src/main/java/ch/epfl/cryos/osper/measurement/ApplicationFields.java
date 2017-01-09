package ch.epfl.cryos.osper.measurement;

import java.time.ZoneId;
import java.util.TimeZone;

/**
 * Contains global application definition fields (constants).
 *
 * @author pertschy@slf.ch, Jun 15, 2015
 */
public class ApplicationFields {

    public static final ZoneId ZONE_ID = ZoneId.of("Europe/Zurich");
    public static final TimeZone TIME_ZONE = TimeZone.getTimeZone(ZONE_ID);

    public static final String SCHEMA = "data_quality.measurement";

    public static final String SECURITY_BUNDLE = "AVAL";
    public static final String BASE_SCAN_PACKAGES =
            "ch.epfl.cryos.osper.measurement.configuration,"
                    + "ch.epfl.cryos.osper.measurement.controller,"
                    + "ch.epfl.cryos.osper.measurement.repository,"
                    + "ch.epfl.cryos.osper.measurement.service,"
                    + "ch.epfl.cryos.osper.measurement.dao,"
                    + "ch.epfl.cryos.osper.measurement.exception,"
                    + "ch.epfl.cryos.osper.measurement.validator"
            ;

    public static final String[] MODEL_SCAN_PACKAGES = {"ch.epfl.cryos.osper.measurement.model"};
    public static final String REPOSITORY_SCAN_PACKAGE = "ch.epfl.cryos.osper.measurement.repository";

    public static final String REST_MAPPING_COMPONENT_INFO = "/info";
    public static final String REST_MEASUREMENT = "/measurement";
    public static final String REST_MEASURAND = "/measurand";
    public static final String REST_MAPPING_EXCEPTION = "exception";


//    public static final int REPOSITORY_SPATIAL_ID = OracleCrsSridMap.SRID_EPSG4326;


    private ApplicationFields() {
        throw new AssertionError();
    }

}
