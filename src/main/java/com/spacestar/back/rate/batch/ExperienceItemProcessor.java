package com.spacestar.back.rate.batch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.spacestar.back.rate.domain.Experience;
import com.spacestar.back.rate.domain.TotalExperience;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExperienceItemProcessor implements ItemProcessor<Experience, TotalExperience> {

	private Map<String, Long> expSum = new HashMap<>();

	@Override
	public TotalExperience process(Experience item) throws Exception{
		expSum.merge(item.getUuid(), (long) item.getChangeExp(), Long::sum);
		return TotalExperience.builder()
			.uuid(item.getUuid())
			.exp(expSum.get(item.getUuid()))
			.build();
	}
}
