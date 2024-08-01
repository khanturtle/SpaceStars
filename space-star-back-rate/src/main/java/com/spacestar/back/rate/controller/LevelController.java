package com.spacestar.back.rate.controller;


import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import com.spacestar.back.rate.dto.req.RateAddReqDto;
import com.spacestar.back.rate.dto.res.LevelExpResDto;
import com.spacestar.back.rate.dto.res.LevelInfoResDto;
import com.spacestar.back.rate.dto.res.LevelResDto;
import com.spacestar.back.rate.service.LevelService;
import com.spacestar.back.rate.service.RateService;
import com.spacestar.back.rate.vo.req.RateAddReqVo;
import com.spacestar.back.rate.vo.res.LevelExpResVo;
import com.spacestar.back.rate.vo.res.LevelInfoResVo;
import com.spacestar.back.rate.vo.res.LevelResVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Level", description = "레벨")
@RequestMapping("/api/v1/rate")
public class LevelController {
    private final LevelService levelService;
    private final ModelMapper modelMapper;

    @GetMapping
    @Operation(summary = "레벨 조회")
    public ResponseEntity<LevelResVo> getLevel(@RequestHeader("UUID") String uuid){
        LevelResDto levelResDto = levelService.getLevel(uuid);
        return new ResponseEntity<>(ResponseSuccess.LEVEL_GET_SUCCESS,modelMapper.map(levelResDto, LevelResVo.class));
    }

    @GetMapping("/info")
    @Operation(summary = "레벨 상세 정보 조회")
    public ResponseEntity<LevelInfoResVo> getLevelInfo(@RequestParam int level){
        LevelInfoResDto levelInfoResDto = levelService.getLevelInfo(level);
        return new ResponseEntity<>(ResponseSuccess.LEVEL_INFO_GET_SUCCESS,modelMapper.map(levelInfoResDto, LevelInfoResVo.class));
    }

    @GetMapping("/exp")
    @Operation(summary = "현재 레벨 보유 경험치 조회")
    public ResponseEntity<LevelExpResVo> getLevelExp(@RequestHeader("UUID") String uuid){
        LevelExpResDto levelExpResDto = levelService.getLevelExp(uuid);
        return new ResponseEntity<>(ResponseSuccess.LEVEL_EXP_GET_SUCCESS,modelMapper.map(levelExpResDto, LevelExpResVo.class));
    }
}
