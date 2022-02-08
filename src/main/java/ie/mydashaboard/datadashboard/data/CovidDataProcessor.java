package ie.mydashaboard.datadashboard.data;
    
import java.time.LocalDate;
//import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import ie.mydashaboard.datadashboard.model.Covid;

public class CovidDataProcessor implements ItemProcessor<CovidInput, Covid> {

  private static final Logger log = LoggerFactory.getLogger(CovidDataProcessor.class);

  @Override
  public Covid process(final CovidInput covidInput) throws Exception {
   
    Covid covid = new Covid();
    covid.setId(Integer.parseInt(covidInput.getId()));
    covid.setLocation(covidInput.getLocation());
    covid.setDate(LocalDate.parse(covidInput.getDate()));
    covid.setVariant(covidInput.getVariant());
    covid.setNumSequences(Integer.parseInt(covidInput.getNum_sequences()));
    covid.setPercSequences(Double.parseDouble(covidInput.getPerc_sequences()));
    covid.setTotalSequences(Integer.parseInt(covidInput.getTotal_sequences()));
   
    return covid;

  
  }

}

