package ie.mydashaboard.datadashboard.data;
    
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import ie.mydashaboard.datadashboard.model.Covid;

public class CovidDataProcessor implements ItemProcessor<CovidInput, Covid> {

  private static final Logger log = LoggerFactory.getLogger(CovidDataProcessor.class);

  @Override
  public Covid process(final CovidInput covidInput) throws Exception {
   
    Covid covid = new Covid();
    covid.setDate(LocalDate.parse(covidInput.getDate()));
    covid.setNumSequences(Integer.parseInt(covidInput.getNum_sequences());
    covid.setVariant(covidInput.getVariant());
    covid.setLocation(covidInput.getLocation());
    covid.setNumSequencestotal(Integer.parseInt(covidInput.getNum_sequences_total()));
    covid.setPercSequences(Integer.parseInt(covidInput.getPerc_sequences()));
    return covid;

  
  }

}

