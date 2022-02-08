package ie.mydashaboard.datadashboard.controller;

import ie.mydashaboard.datadashboard.repository.CountryRepository;
import ie.mydashaboard.datadashboard.model.Country;
import ie.mydashaboard.datadashboard.model.Covid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CountryController {


    private CountryRepository countryRepository;
    
    
    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
        
    }

    @GetMapping("/country/{countryName}")
    public Country getCountry(@PathVariable String countryName){
        return this.countryRepository.findByCountryName(countryName);
    }

    
    
}
