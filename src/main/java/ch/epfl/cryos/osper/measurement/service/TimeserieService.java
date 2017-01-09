package ch.epfl.cryos.osper.measurement.service;

import ch.epfl.cryos.osper.measurement.dto.TimeserieDto;
import ch.epfl.cryos.osper.measurement.dto.TimeserieQueryDto;
import ch.epfl.cryos.osper.measurement.model.Timeserie;
import ch.epfl.cryos.osper.measurement.repository.MeasurementRepository;
import ch.epfl.cryos.osper.measurement.repository.TimeserieRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kryvych on 23/12/16.
 */
@Service
public class TimeserieService {

    private final TimeserieRepository timeserieRepository;
    private final MeasurementRepository measurementRepository;

    @Inject
    public TimeserieService(TimeserieRepository timeserieRepository, MeasurementRepository measurementRepository) {
        this.timeserieRepository = timeserieRepository;
        this.measurementRepository = measurementRepository;
    }


    public TimeserieDto getTimeserieForQuery(Long id, TimeserieQueryDto query) {

        Timeserie timeserieInfo = null;
        if (query.isIncludeInfo()) {
           timeserieInfo = timeserieRepository.findOne(id);
        }

        if (!query.isIncludeData()) {
            return new TimeserieDto(timeserieInfo, null);
        }
        if (query.getFrom() != null && query.getUntil() != null) {
            return new TimeserieDto(timeserieInfo,
                    measurementRepository.findByTimeserieIdAndMeasureDateBetween(id, query.getFrom(), query.getUntil()));
        }
        if(query.getFrom() != null) {
            return new TimeserieDto(timeserieInfo,
                    measurementRepository.findByTimeserieIdAndMeasureDateGreaterThanEqual(id, query.getFrom()));
        }
        if(query.getUntil() != null) {
            return new TimeserieDto(timeserieInfo,
                    measurementRepository.findByTimeserieIdAndMeasureDateLessThanEqual(id, query.getUntil()));
        }

        return new TimeserieDto(timeserieInfo,
                measurementRepository.findByTimeserieId(id));

    }

    public List<TimeserieDto> getForStation(Long stationId) {

        return timeserieRepository.findByStationId(stationId)
                .stream()
                .map(x -> new TimeserieDto(x, null))
                .collect(Collectors.toList());

    }

}
