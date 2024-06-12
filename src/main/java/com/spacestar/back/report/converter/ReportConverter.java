package com.spacestar.back.report.converter;

import com.spacestar.back.report.domain.Report;
import com.spacestar.back.report.dto.req.ReportReqDto;

public class ReportConverter {
    public static Report toEntity(String uuid, ReportReqDto reqDto){
        return Report.builder()
                .fromMember(uuid)
                .toMember(reqDto.getToMember())
                .content(reqDto.getContent())
                .picture(reqDto.getPicture())
                .build();
    }
}
