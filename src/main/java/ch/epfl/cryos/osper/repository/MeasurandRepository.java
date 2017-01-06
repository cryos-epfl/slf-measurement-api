package ch.epfl.cryos.osper.repository;

import ch.epfl.cryos.osper.model.Measurand;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kryvych on 29/12/16.
 */
public interface MeasurandRepository extends JpaRepository<Measurand, Long>{
}
