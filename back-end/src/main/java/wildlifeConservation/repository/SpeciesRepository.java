package wildlifeConservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wildlifeConservation.domain.entities.Species;

import java.util.List;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, String> {

    @Query("SELECT s FROM Species AS s ORDER by s.name")
    List<Species> findAllOrderedByName();

}
