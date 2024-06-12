package com.spacestar.back.block.controller;

import com.spacestar.back.block.dto.req.BlockReqDto;
import com.spacestar.back.block.service.BlockService;
import com.spacestar.back.block.vo.req.BlockReqVo;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Block", description = "차단")
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile/block")
public class BlockController {

    private final BlockService blockService;
    private final ModelMapper mapper;

    @Operation(summary = "차단 추가")
    @PostMapping("/add")
    public ResponseEntity<Void> addBlock(@RequestHeader String uuid, @RequestBody BlockReqVo blockReqVo){

        blockService.addBlock(uuid, mapper.map(blockReqVo, BlockReqDto.class));
        return new ResponseEntity<>(ResponseSuccess.MAIN_PROFILE_IMAGE_SELECT_SUCCESS)
    }
}
