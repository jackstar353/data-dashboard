package ie.mydashaboard.datadashboard.data;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import ie.mydashaboard.datadashboard.model.Covid;
import ie.mydashaboard.datadashboard.model.Covid;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final String[] FIELD_NAMES = new String[]{
        "location", "date", "variant", "num_sequences" , "perc_sequences", "num_sequences_total"
    };

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<CovidInput> reader() {
        return new FlatFileItemReaderBuilder<CovidInput>()
                .name("CovidItemReader")
                .resource(new ClassPathResource("covid-variants.csv"))
                .delimited()
                .names(FIELD_NAMES)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<CovidInput>() {
                    {
                        setTargetType(CovidInput.class);
                    }
                })
                .build();
    }

    @Bean
    public CovidDataProcessor processor() {
        return new CovidDataProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Covid> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Covid>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO covid (location, date, variant, num_sequences, perc_sequences, num_sequences_total) "
                + " VALUES (:location, :date, :variant, :num_sequences, :perc_sequences, :num_sequences_total)")
                 .build();
    }
}