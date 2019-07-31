package wildlifeConservation.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wildlifeConservation.domain.entities.Species;
import wildlifeConservation.domain.models.service.SpeciesServiceModel;
import wildlifeConservation.repository.SpeciesRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpeciesServiceImpl implements SpeciesService {
    private SpeciesRepository speciesRepository;
    private ModelMapper modelMapper;

    @Autowired
    public SpeciesServiceImpl(SpeciesRepository speciesRepository, ModelMapper modelMapper) {
        this.speciesRepository = speciesRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SpeciesServiceModel> findAllOrderedByName() {
        List<Species> speciesList = this.speciesRepository.findAllOrderedByName();
        List<SpeciesServiceModel> speciesServiceModelList = speciesList.stream()
                .map(species -> this.modelMapper.map(species, SpeciesServiceModel.class))
                .collect(Collectors.toList());

        return speciesServiceModelList;
    }

    @Override
    public SpeciesServiceModel saveSpecies(SpeciesServiceModel speciesServiceModel) {
        Species species = this.modelMapper.map(speciesServiceModel, Species.class);
        Species savedSpecies = this.speciesRepository.save(species);
        SpeciesServiceModel mappedSpeciesServiceModel = this.modelMapper
                .map(savedSpecies, SpeciesServiceModel.class);

        return mappedSpeciesServiceModel;
    }

    @Override
    public boolean editSpecies(SpeciesServiceModel speciesServiceModel) {
        String id = speciesServiceModel.getId();
        Species species = this.speciesRepository.getOne(id);
        if (species != null) {
            species = this.modelMapper.map(speciesServiceModel, Species.class);
            this.speciesRepository.save(species);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteSeciesById(String id) {
        try {
            this.speciesRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
