package com.spacestar.back.config.batch;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class BatchScheduler {

	private final JobLauncher jobLauncher;
	private final Job experienceAggregationJob;

	@Scheduled(cron = "0 0 * * * *")
	public void runBatchJob(){
		try {
			JobParameters jobParameters = new JobParametersBuilder()
				.addLocalDateTime("time", LocalDateTime.now())
				.toJobParameters();
			jobLauncher.run(experienceAggregationJob, jobParameters);
		} catch (Exception e) {
			log.info("Error occurred while running the batch job", e);
		}
	}
}
