package wildlifeConservation.services;

import org.springframework.stereotype.Service;
import wildlifeConservation.domain.models.service.SpeciesServiceModel;

import java.util.List;

@Service
public interface SpeciesService {

    List<SpeciesServiceModel> findAllOrderedByName();

    SpeciesServiceModel saveSpecies(SpeciesServiceModel speciesServiceModel);

    boolean editSpecies(SpeciesServiceModel speciesServiceModel);

    boolean deleteSeciesById(String id);

    SpeciesServiceModel findById(String id);
}
