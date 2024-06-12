package com.spacestar.back.report.controller;

import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import com.spacestar.back.report.dto.req.ReportReqDto;
import com.spacestar.back.report.service.ReportService;
import com.spacestar.back.report.vo.req.ReportReqVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Report", description = "신고")
@RequestMapping("/api/v1/report")
public class ReportController {
    private final ReportService reportService;
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<Void> createReport(@RequestHeader("UUID") String uuid,
                                             @RequestBody ReportReqVo reqVo) {
        reportService.createReport(uuid, mapper.map(reqVo, ReportReqDto.class));
        return new ResponseEntity<>(ResponseSuccess.REPORT_ADD_SUCCESS);
    }
}