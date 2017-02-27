package ch.epfl.cryos.osper.measurement.controller;

import ch.epfl.cryos.osper.measurement.ApplicationFields;
import ch.epfl.cryos.osper.measurement.dto.TimeserieDto;
import ch.epfl.cryos.osper.measurement.dto.TimeserieQueryDto;
import ch.epfl.cryos.osper.measurement.model.Group;
import ch.epfl.cryos.osper.measurement.model.Measurand;
import ch.epfl.cryos.osper.measurement.model.Measurement;
import ch.epfl.cryos.osper.measurement.model.Timeserie;
import ch.epfl.cryos.osper.measurement.service.MeasurandService;
import ch.epfl.cryos.osper.measurement.service.TimeserieService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.inject.Inject;
import java.util.List;


/**
 * Created by kryvych on 23/12/16.
 */
@RestController
@RequestMapping(ApplicationFields.REST_MEASUREMENT)
@Api(value = "Controller for accessing measurement data")
public class TimeseriesController {

    private final static String DATE_TIME_FORMATS = "2016-11-17 or 2016-11-17T13:00Z or 2016-11-17T13:00:00Z";

    private final MeasurandService measurandService;
    private final TimeserieService timeserieService;

    @Inject
    public TimeseriesController(MeasurandService measurandService, TimeserieService timeserieService) {
        this.measurandService = measurandService;
        this.timeserieService = timeserieService;
    }


    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(
            value = "timeseries/{timeserieId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get data", notes = "Returns timeserie metadata and data JSON format. ", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "includeData", value = "Include data", required = false, allowMultiple = false, paramType = "query", dataType = "boolean", defaultValue = "true"),
            @ApiImplicitParam(name = "includeInfo", value = "Include info", required = false, allowMultiple = false, paramType = "query", dataType = "boolean", defaultValue = "true"),
            @ApiImplicitParam(name = "from", value = "Start of the timespan " + DATE_TIME_FORMATS, required = false, paramType = "query", defaultValue = "2016-11-17T13:00Z"),
            @ApiImplicitParam(name = "until", value = "End of the timespan " + DATE_TIME_FORMATS, required = false, paramType = "query", defaultValue = "2017-01-05T13:00Z"),
            @ApiImplicitParam(name = "limit", value = "Row number limit ", required = false, paramType = "query")
    })

    public TimeserieDto getTimeserieById(
            @PathVariable(value = "timeserieId") @ApiParam(value = "Timeserie ID", required = true) Long timeserieId,
            @ApiIgnore TimeserieQueryDto query
    ) {

        return timeserieService.getTimeserieForQuery(timeserieId, query);
    }


    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(
            value = "timeseries/{timeserieId}/info",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get data", notes = "Returns timeserie metadata and data JSON format. ", response = String.class)

    public Timeserie getTimeserieInfoById(
            @PathVariable(value = "timeserieId") @ApiParam(value = "Timeserie ID", required = true) Long timeserieId
    ) {

        return timeserieService.getTimeserieInfo(timeserieId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(
            value = "timeseries/{timeserieId}/data",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get data", notes = "Returns timeserie data JSON format. ", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "from", value = "Start of the timespan " + DATE_TIME_FORMATS, required = false, paramType = "query", defaultValue = "2016-11-17T13:00Z"),
            @ApiImplicitParam(name = "until", value = "End of the timespan " + DATE_TIME_FORMATS, required = false, paramType = "query", defaultValue = "2016-11-17T18:00Z"),
            @ApiImplicitParam(name = "limit", value = "Row number limit ", required = false, paramType = "query")
    })

    public List<Measurement> getTimeserieDataById(
            @PathVariable(value = "timeserieId") @ApiParam(value = "Timeserie ID", required = true) Long timeserieId,
            @ApiIgnore TimeserieQueryDto query
    ) {

        System.out.println("!!! QUERY = " + query);

        return timeserieService.getMeasurementsForTimeserie(timeserieId, query);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(
            value = "timeseries",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get data", notes = "Returns timeserie metadata for a given station Id in JSON format. ", response = String.class)


    public List<Timeserie> getAllTimeseries() {
        return timeserieService.getAllTimeseries();
    }


    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(
            value = "station/{stationId}/timeseries",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get data", notes = "Returns timeserie metadata for a given station Id in JSON format. ", response = String.class)


    public List<Timeserie> getTimeseriesForStation(
            @PathVariable(value = "stationId") @ApiParam(value = "station id", required = true) Long stationId
    ) {

        return timeserieService.getForStation(stationId);
    }


    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(
            value = "measurands",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get data", notes = "Returns all available measurands ", response = String.class)

    public List<Measurand> allMeasurands(
            @RequestParam(value = "groupCode", required = false) String groupCode) {

        if (!StringUtils.isEmpty(groupCode)) {
            return measurandService.findByGroupsCode(groupCode);
        }
        return measurandService.getAllMeasurands();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(
            value = "groups",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get data", notes = "Returns all available groups ", response = String.class)

    public List<Group> allMeasurands() {
        return measurandService.getAllGroups();
    }
}
