package com.alansep.spring_batch_lab.config.studentlistreading;

import com.alansep.spring_batch_lab.config.studentscoreprocessing.StudentScoreProcessingReader;
import com.alansep.spring_batch_lab.config.studentscoreprocessing.StudentScoreProcessingWriter;
import com.alansep.spring_batch_lab.model.dto.StudentScore;
import com.alansep.spring_batch_lab.model.entity.Student;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfiguration {

    @Bean
    public FlatFileItemReader<StudentScore> reader() {
        return new FlatFileItemReaderBuilder<StudentScore>()
                .name("studentItemReader")
                .resource(new ClassPathResource("scores.csv"))
                .delimited()
                .names("id", "firstName", "lastName", "firstScore", "secondScore", "thirdScore", "fourthScore")
                .targetType(StudentScore.class).build();
    }

    @Bean
    public StudentItemProcessor processor() {
        return new StudentItemProcessor();
    }

    @Bean
    public Job importUserJob(JobRepository jobRepository, @Qualifier("step1") Step step1, @Qualifier("step2") Step step2, JobCompletionNotificationListener listener) {
        return new JobBuilder("importUserJob", jobRepository)
                .listener(listener)
                .start(step1)
                .next(step2)
                .build();
    }

    @Bean(name = "step1")
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager, FlatFileItemReader<StudentScore> reader, StudentItemProcessor processor, ItemWriter<StudentScore> writer) {
        return new StepBuilder("step1", jobRepository)
                .<StudentScore, StudentScore>chunk(50, transactionManager)
                .allowStartIfComplete(true)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean(name = "step2")
    public Step step2(JobRepository jobRepository, PlatformTransactionManager transactionManager, StudentScoreProcessingReader reader, StudentScoreProcessingWriter writer) {
        return new StepBuilder("step2", jobRepository)
                .<Student, Student>chunk(50, transactionManager)
                .allowStartIfComplete(true)
                .reader(reader)
                .writer(writer)
                .build();
    }

}
