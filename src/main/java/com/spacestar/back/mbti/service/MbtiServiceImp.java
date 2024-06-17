package com.spacestar.back.mbti.service;

import com.spacestar.back.global.GlobalException;
import com.spacestar.back.global.ResponseStatus;
import com.spacestar.back.mbti.domain.Mbti;
import com.spacestar.back.mbti.dto.res.MbtiListResDto;
import com.spacestar.back.mbti.dto.res.MbtiNameResDto;
import com.spacestar.back.mbti.repository.MbtiRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MbtiServiceImp implements MbtiService {


    private final MbtiRepository mbtiRepository;
    private final ModelMapper mapper;

    @Override
    public List<MbtiListResDto> getMbtiList() {

        return mbtiRepository.findAll().stream()
                .map(mbti -> mapper.map(mbti, MbtiListResDto.class))
                .toList();
    }

    @Override
    public MbtiNameResDto getMbtiName(Long id) {

        Mbti mbti = mbtiRepository.findById(id)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NOT_EXIST_MBTI));

        return mapper.map(mbti, MbtiNameResDto.class);
    }

}
