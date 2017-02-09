package ch.epfl.cryos.osper.measurement.service;

import ch.epfl.cryos.osper.measurement.model.Group;
import ch.epfl.cryos.osper.measurement.model.Measurand;
import ch.epfl.cryos.osper.measurement.repository.GroupRepository;
import ch.epfl.cryos.osper.measurement.repository.MeasurandRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by kryvych on 29/12/16.
 */
@Service
public class MeasurandService {

    private final MeasurandRepository repository;
    private final GroupRepository groupRepository;

    @Inject
    public MeasurandService(MeasurandRepository repository, GroupRepository groupRepository) {
        this.repository = repository;
        this.groupRepository = groupRepository;
    }

    public List<Measurand> getAllMeasurands() {
        return repository.findAll();
    }

    public List<Measurand> findByGroupsCode(String groupCode) {
        return repository.findByGroupsCode(groupCode.toUpperCase());
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

}
