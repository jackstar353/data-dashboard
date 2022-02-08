package ie.mydashaboard.datadashboard.data;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
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

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final String[] FIELD_NAMES = new String[] {
           "id", "location", "date", "variant", "num_sequences", "perc_sequences", "total_sequences"
    };

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<CovidInput> reader() {
        return new FlatFileItemReaderBuilder<CovidInput>()
                .name("CovidItemReader")
                .resource(new ClassPathResource("covid-variants-index.csv"))
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
                .sql("INSERT INTO covid (id,location, date, variant, num_sequences, perc_sequences, total_sequences) "
                        + " VALUES (:id, :location, :date, :variant, :numSequences, :percSequences, :totalSequences)")
                        .dataSource(dataSource).build();
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Covid> writer) {
        return stepBuilderFactory.get("step1")
                .<CovidInput, Covid>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }
}