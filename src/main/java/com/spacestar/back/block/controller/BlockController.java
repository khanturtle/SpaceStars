package com.spacestar.back.block.controller;

import com.spacestar.back.block.dto.req.BlockReqDto;
import com.spacestar.back.block.dto.res.BlockListResDto;
import com.spacestar.back.block.service.BlockService;
import com.spacestar.back.block.vo.req.BlockReqVo;
import com.spacestar.back.block.vo.res.BlockExistResVo;
import com.spacestar.back.block.vo.res.BlockListResVo;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Block", description = "차단")
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile/block")
public class BlockController {

    private final BlockService blockService;
    private final ModelMapper mapper;

    @Operation(summary = "차단")
    @PostMapping("/add")
    public ResponseEntity<Void> addBlock(@RequestHeader("UUID") String uuid, @RequestBody BlockReqVo blockReqVo){

        blockService.addBlock(uuid, mapper.map(blockReqVo, BlockReqDto.class));
        return new ResponseEntity<>(ResponseSuccess.BLOCK_ADD_SUCCESS);
    }

    @Operation(summary = "차단 취소")
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteBlock(@RequestHeader("UUID") String uuid, @RequestBody BlockReqVo blockReqVo){

        blockService.deleteBlock(uuid, mapper.map(blockReqVo, BlockReqDto.class));
        return new ResponseEntity<>(ResponseSuccess.BLOCK_DELETE_SUCCESS);
    }

    @Operation(summary = "차단 여부 조회")
    @GetMapping("/exist/{targetUuid}")
    public ResponseEntity<BlockExistResVo> existBlock(@RequestHeader("UUID") String uuid,
                                                      @PathVariable("targetUuid") String targetUuid){

        return new ResponseEntity<>(ResponseSuccess.BLOCK_EXIST_SUCCESS,
                mapper.map(blockService.existBlock(uuid, targetUuid), BlockExistResVo.class));
    }

    @Operation(summary = "차단 목록 조회")
    @GetMapping("/list")
    public ResponseEntity<List<BlockListResVo>> blockList(@RequestHeader("UUID") String uuid){

        return new ResponseEntity<>(ResponseSuccess.BLOCK_LIST_SELECT_SUCCESS,
                blockService.blockList(uuid).stream()
                        .map(blockListResDto -> mapper.map(blockListResDto, BlockListResVo.class))
                        .toList());
    }
}
