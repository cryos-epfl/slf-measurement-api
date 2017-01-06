package ch.epfl.cryos.osper.repository;

import ch.epfl.cryos.osper.model.Timeserie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by kryvych on 23/12/16.
 */
public interface TimeserieRepository extends JpaRepository<Timeserie, Long> {

    List<Timeserie> findByStationId(Long stationId);

}
