package com.spacestar.back.rate.batch;

import java.util.List;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.spacestar.back.rate.domain.TotalExperience;
import com.spacestar.back.rate.repository.TotalExperienceRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class TotalExperienceItemWriter implements ItemWriter<TotalExperience> {

	private final TotalExperienceRepository totalExperienceRepository;

	@Override
	public void write(Chunk<? extends TotalExperience> items) throws Exception {
		items.forEach(totalExp -> {
			TotalExperience existing = totalExperienceRepository.findByUuid(totalExp.getUuid())
				.orElse(TotalExperience.builder()
					.uuid(totalExp.getUuid())
					.exp(0L)
					.build());

			TotalExperience updated = TotalExperience.builder()
				.id(existing.getId())
				.uuid(existing.getUuid())
				.exp(existing.getExp() + totalExp.getExp())
				.build();

			totalExperienceRepository.save(updated);
			log.info("Writing item: {}", updated);
		});
	}
}
