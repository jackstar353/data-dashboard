package ie.mydashaboard.datadashboard.data;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ie.mydashaboard.datadashboard.model.Country;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final EntityManager em;

    @Autowired
    public JobCompletionNotificationListener(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            Map<String, Country> countryData = new HashMap<>();

            em.createQuery("select l.location, count(*) from Covid l group by l.location", Object[].class)
                    .getResultList()
                    .stream()
                    .map(e -> new Country((String) e[0], (long) e[1]))
                    .forEach(country -> countryData.put(country.getCountryName(), country));

            // jdbcTemplate.query("SELECT location, date , variant FROM covid",
            // (rs, row) -> "Location " + rs.getString(1) + "Date " + rs.getString(2) +
            // "Variant " + rs.getString(3))

            // .forEach(str -> System.out.println(str));
            countryData.values().forEach(country -> em.persist(country));
            countryData.values().forEach(country -> System.out.println(country));
        }
        
    }
    
}
