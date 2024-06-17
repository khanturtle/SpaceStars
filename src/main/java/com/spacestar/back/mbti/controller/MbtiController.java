package com.spacestar.back.mbti.controller;

import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import com.spacestar.back.mbti.service.MbtiService;
import com.spacestar.back.mbti.vo.res.MbtiListResVo;
import com.spacestar.back.mbti.vo.res.MbtiNameResVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Mbti", description = "Mbti API")
@RequiredArgsConstructor
@RequestMapping("/api/v1/data/mbti")
public class MbtiController {


    private final MbtiService mbtiService;
    private final ModelMapper mapper;

    @Operation(summary = "Mbti 목록 조회")
    @GetMapping("/list")
    public ResponseEntity<List<MbtiListResVo>> getMbtiList(){

        List<MbtiListResVo> mbtiList = mbtiService.getMbtiList().stream()
                .map(mbti -> mapper.map(mbti, MbtiListResVo.class))
                .toList();

        return new ResponseEntity<>(ResponseSuccess.MBTI_LIST_SUCCESS, mbtiList);
    }

    @Operation(summary = "Mbti id로 이름 조회")
    @GetMapping("/{id}")
    public ResponseEntity<MbtiNameResVo> getMbtiName(Long id){

        return new ResponseEntity<>(ResponseSuccess.MBTI_NAME_SELECT_SUCCESS,
                mapper.map(mbtiService.getMbtiName(id), MbtiNameResVo.class));
    }
}
