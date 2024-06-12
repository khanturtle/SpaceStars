package com.spacestar.back.report.service;

import com.spacestar.back.profile.repository.ProfileRepository;
import com.spacestar.back.report.converter.ReportConverter;
import com.spacestar.back.report.dto.req.ReportReqDto;
import com.spacestar.back.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{
    private final ReportRepository reportRepository;
    private final ProfileRepository profileRepository;
    @Override
    public void createReport(String uuid, ReportReqDto reqDto) {
        reportRepository.save(ReportConverter.toEntity(uuid,reqDto));
        profileRepository.increaseReportCount(reqDto.getToMember());
    }
}
