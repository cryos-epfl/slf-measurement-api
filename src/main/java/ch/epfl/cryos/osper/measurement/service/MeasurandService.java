package ch.epfl.cryos.osper.measurement.service;

import ch.epfl.cryos.osper.measurement.model.Measurand;
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

    @Inject
    public MeasurandService(MeasurandRepository repository) {
        this.repository = repository;
    }

    public List<Measurand> getAllMeasurands() {
        return repository.findAll();
    }
}
