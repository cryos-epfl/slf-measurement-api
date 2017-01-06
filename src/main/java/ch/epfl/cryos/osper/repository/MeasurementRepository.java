package ch.epfl.cryos.osper.repository;

import ch.epfl.cryos.osper.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by kryvych on 05/01/17.
 */
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    List<Measurement> findByTimeserieIdAndMeasureDateBetween(Long timeserieId, Date startDate, Date endDate);

    List<Measurement>  findByTimeserieIdAndMeasureDateGreaterThanEqual(Long id, Date from);

    List<Measurement>  findByTimeserieIdAndMeasureDateLessThanEqual(Long id, Date until);

    List<Measurement>  findByTimeserieId(Long id);
}
