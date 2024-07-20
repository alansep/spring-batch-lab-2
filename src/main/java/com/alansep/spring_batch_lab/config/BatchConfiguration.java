package com.alansep.spring_batch_lab.config;

import com.alansep.spring_batch_lab.model.dto.PersonDTO;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfiguration {

    @Bean
    public FlatFileItemReader<PersonDTO> reader() {
        return new FlatFileItemReaderBuilder<PersonDTO>().name("personItemReader").resource(new ClassPathResource("sample-data.csv")).delimited().names("firstName", "lastName").targetType(PersonDTO.class).build();
    }

    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

    @Bean
    public Job importUserJob(JobRepository jobRepository, Step step1, JobCompletionNotificationListener listener) {
        return new JobBuilder("importUserJob", jobRepository).listener(listener).start(step1).build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager, FlatFileItemReader<PersonDTO> reader, PersonItemProcessor processor, ItemWriter<PersonDTO> writer) {
        return new StepBuilder("step1", jobRepository).<PersonDTO, PersonDTO>chunk(3, transactionManager).allowStartIfComplete(true).reader(reader).processor(processor).writer(writer).build();
    }

}
