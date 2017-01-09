//package ch.epfl.cryos.osper.measurement.controller;
//
//import ch.epfl.cryos.osper.measurement.ApplicationFields;
//import ch.epfl.cryos.osper.measurement.dto.TimeserieDto;
//import ch.epfl.cryos.osper.measurement.model.Measurand;
//import ch.epfl.cryos.osper.measurement.service.MeasurandService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * Created by kryvych on 09/01/17.
// */
//@RestController
//@RequestMapping(ApplicationFields.REST_MEASURAND)
//@Api(value = "Controller for accessing measurands")
//public class MeasurandController {
//
//    private final MeasurandService measurandService;
//
//    public MeasurandController(MeasurandService measurandService) {
//        this.measurandService = measurandService;
//    }
//
//    @ResponseStatus(value = HttpStatus.OK)
//    @RequestMapping(
//            value = "measurands",
//            method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    @ApiOperation(value = "Get data", notes = "Returns timeserie metadata for a given station Id in JSON format. ", response = String.class)
//
//
//    public List<Measurand> getAllMeasurands(
//            @PathVariable(value = "stationId") @ApiParam(value = "station id", required = true) Long stationId
//    ) {
//
//        return timeserieService.getForStation(stationId);
//    }
//
//
//}
