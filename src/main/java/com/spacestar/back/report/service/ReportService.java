package com.spacestar.back.report.service;

import com.spacestar.back.report.dto.req.ReportReqDto;
import org.springframework.stereotype.Service;

@Service
public interface ReportService {
    void createReport(String uuid, ReportReqDto map);
}
