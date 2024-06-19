package com.spacestar.back.report.service;

import com.spacestar.back.report.dto.req.ReportReqDto;


public interface ReportService {
    void createReport(String uuid, ReportReqDto reqDto);
}
