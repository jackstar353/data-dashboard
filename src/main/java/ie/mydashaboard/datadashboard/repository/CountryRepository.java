package ie.mydashaboard.datadashboard.repository;

import org.springframework.data.repository.CrudRepository;

import ie.mydashaboard.datadashboard.model.Country;



public interface CountryRepository extends CrudRepository<Country, Long>{

    Country findByCountryName(String countryName);
    
}
