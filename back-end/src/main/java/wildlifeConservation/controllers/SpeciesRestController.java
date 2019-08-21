package wildlifeConservation.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wildlifeConservation.domain.entities.enums.ConservationStatus;
import wildlifeConservation.domain.entities.enums.Kingdom;
import wildlifeConservation.domain.models.service.SpeciesServiceModel;
import wildlifeConservation.domain.models.view.SpeciesViewModel;
import wildlifeConservation.services.SpeciesService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SpeciesRestController {
    private SpeciesService speciesService;
    private ModelMapper modelMapper;

    @Autowired
    public SpeciesRestController(SpeciesService speciesService, ModelMapper modelMapper) {
        this.speciesService = speciesService;
        this.modelMapper = modelMapper;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/")
    public List<SpeciesViewModel> getAllSpeciesOrderedByName() {
        List<SpeciesServiceModel> allSpeciesServiceModel = this.speciesService.findAllOrderedByName();
        List<SpeciesViewModel> allSpeciesViewModel = allSpeciesServiceModel.stream()
                .map(species -> this.modelMapper.map(species, SpeciesViewModel.class))
                .collect(Collectors.toList());

        return allSpeciesViewModel;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/save-species")
    public ResponseEntity<?> saveSpecies(@RequestBody SpeciesViewModel speciesViewModel) {
        SpeciesServiceModel speciesServiceModel = this.modelMapper
                .map(speciesViewModel, SpeciesServiceModel.class);
        SpeciesServiceModel savedSpeciesServiceModel = this.speciesService
                .saveSpecies(speciesServiceModel);
        return new ResponseEntity<>(savedSpeciesServiceModel, HttpStatus.OK);

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/edit-species/{id}")
    public ResponseEntity<?> editSpecies(@PathVariable("id") String id) {
        SpeciesServiceModel speciesServiceModel = this.speciesService.findById(id);
        SpeciesViewModel speciesViewModel = this.modelMapper.map(speciesServiceModel, SpeciesViewModel.class);

        return new ResponseEntity<>(speciesViewModel, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/edit-species/{id}")
    public ResponseEntity<?> editSpecies(@PathVariable("id") String id, @RequestBody SpeciesViewModel speciesViewModel) {
        SpeciesServiceModel speciesServiceModel = this.modelMapper
                .map(speciesViewModel, SpeciesServiceModel.class);
        this.speciesService.editSpecies(speciesServiceModel);

        SpeciesServiceModel foundSpeciesServiceModel = this.speciesService.findById(id);
        SpeciesViewModel mappedSpeciesViewModel = this.modelMapper.map(foundSpeciesServiceModel, SpeciesViewModel.class);

        return new ResponseEntity<>(mappedSpeciesViewModel, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/delete-species/{id}")
    public ResponseEntity<?> deleteSpecies(@PathVariable("id") String id) {
        this.speciesService.deleteSeciesById(id);

        List<SpeciesServiceModel> allSpeciesServiceModel = this.speciesService.findAllOrderedByName();
        List<SpeciesViewModel> allSpeciesViewModel = allSpeciesServiceModel.stream()
                .map(species -> this.modelMapper.map(species, SpeciesViewModel.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(allSpeciesViewModel, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/all-species")
    public List<SpeciesViewModel> getAllSpecies() {
        List<SpeciesServiceModel> allSpeciesServiceModel = this.speciesService.findAllOrderedByName();
        List<SpeciesViewModel> allSpeciesViewModel = allSpeciesServiceModel.stream()
                .map(species -> this.modelMapper.map(species, SpeciesViewModel.class))
                .collect(Collectors.toList());

        return allSpeciesViewModel;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/kingdoms")
    public Kingdom[] getKingdoms() {
        Kingdom[] kingdoms = Kingdom.values();

        return kingdoms;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/conservation-statuses")
    public ConservationStatus[] getConservationStatuses() {
        ConservationStatus[] conservationStatuses = ConservationStatus.values();

        return conservationStatuses;
    }
}
