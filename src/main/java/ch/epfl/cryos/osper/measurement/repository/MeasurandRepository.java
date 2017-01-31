package ch.epfl.cryos.osper.measurement.repository;

import ch.epfl.cryos.osper.measurement.model.Measurand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by kryvych on 29/12/16.
 */
public interface MeasurandRepository extends JpaRepository<Measurand, Long>{

     List<Measurand> findByGroupsCode(String group);
}
