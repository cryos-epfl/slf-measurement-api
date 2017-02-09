package ch.epfl.cryos.osper.measurement.repository;

import ch.epfl.cryos.osper.measurement.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kryvych on 29/12/16.
 */
public interface GroupRepository extends JpaRepository<Group, Long>{

}
