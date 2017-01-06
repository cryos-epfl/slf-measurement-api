package ch.epfl.cryos.osper.controller;

import ch.epfl.cryos.osper.ApplicationFields;
import ch.epfl.cryos.osper.dto.TimeserieDto;
import ch.epfl.cryos.osper.dto.TimeserieQueryDto;
import ch.epfl.cryos.osper.service.MeasurandService;
import ch.epfl.cryos.osper.service.TimeserieService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.inject.Inject;
import java.util.List;

import static ch.slf.pro.common.util.time.ISOTimeFormat.ZONED_DATE_TIME;

/**
 * Created by kryvych on 23/12/16.
 */
@RestController
@RequestMapping(ApplicationFields.REST_MEASUREMENT)
@Api(value = "Controller for accessing measurement data")
public class TimeseriesController {


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
            @ApiImplicitParam(name = "from", value = "Start of the timespan " + ZONED_DATE_TIME, required = false, paramType = "query", defaultValue = "2016-11-17T13:00Z"),
            @ApiImplicitParam(name = "until", value = "End of the timespan " + ZONED_DATE_TIME, required = false, paramType = "query", defaultValue = "2017-01-05T13:00Z"),
            @ApiImplicitParam(name = "limit", value = "Row number limit ", required = false, paramType = "query")
    })

    public TimeserieDto getTimeserieById(
            @PathVariable(value = "timeserieId") @ApiParam(value = "Timeserie ID", required = true) Long timeserieId,
            @ApiIgnore TimeserieQueryDto query
//            @RequestParam(value ="from", required = false)
//            @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mmZ") Date from,
//            @RequestParam(value ="to", required = false)
//            @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mmZ") Date to
    ) {

        System.out.println("!!! QUERY = " + query);

        return timeserieService.getTimeserieForQuery(timeserieId, query);
    }


    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(
            value = "station/{stationId}/timeseries",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get data", notes = "Returns timeserie metadata for a given station Id in JSON format. ", response = String.class)


    public List<TimeserieDto> getTimeseriesForStation(
            @PathVariable(value = "stationId") @ApiParam(value = "station id", required = true) Long stationId
    ) {

        return timeserieService.getForStation(stationId);
    }


}
