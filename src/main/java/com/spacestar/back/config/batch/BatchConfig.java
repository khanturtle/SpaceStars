package com.spacestar.back.config.batch;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.spacestar.back.rate.batch.ExperienceItemProcessor;
import com.spacestar.back.rate.batch.TotalExperienceItemWriter;
import com.spacestar.back.rate.domain.Experience;
import com.spacestar.back.rate.domain.TotalExperience;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

	private final ExperienceItemProcessor experienceItemProcessor;
	private final TotalExperienceItemWriter experienceItemWriter;
	private final EntityManagerFactory entityManagerFactory;

	@Bean
	public JpaPagingItemReader<Experience> experienceJpaPagingItemReader() {
		return new JpaPagingItemReaderBuilder<Experience>()
			.name("experienceItemReader")
			.entityManagerFactory(entityManagerFactory)
			.queryString("SELECT e FROM Experience e WHERE e.createdAt >= :startTime")
			.parameterValues(Map.of("startTime", LocalDateTime.now().minusHours(1)))
			.pageSize(100)
			.build();
	}
	@Bean
	public Job experienceAggregationJob(JobRepository jobRepository, PlatformTransactionManager transactionManager){
		return new JobBuilder("ExperienceAggregationJob", jobRepository)
			.start(experienceAggregationStep(jobRepository, transactionManager))
			.build();
	}

	@Bean
	public Step experienceAggregationStep(JobRepository jobRepository, PlatformTransactionManager transactionManager){
		return new StepBuilder("experienceAggregationStep", jobRepository)
			.<Experience, TotalExperience> chunk(100, transactionManager)
			.reader(experienceJpaPagingItemReader())
			.processor(experienceItemProcessor)
			.writer(experienceItemWriter)
			.build();
	}
}
