package com.spacestar.back.mbti.service;

import com.spacestar.back.mbti.dto.res.MbtiListResDto;
import com.spacestar.back.mbti.dto.res.MbtiNameResDto;

import java.util.List;

public interface MbtiService {

    List<MbtiListResDto> getMbtiList();

    MbtiNameResDto getMbtiName(Long id);
}
