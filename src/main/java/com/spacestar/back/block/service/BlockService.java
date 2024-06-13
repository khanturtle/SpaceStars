package com.spacestar.back.block.service;

import com.spacestar.back.block.dto.req.BlockReqDto;
import com.spacestar.back.block.dto.res.BlockExistResDto;
import com.spacestar.back.block.dto.res.BlockListResDto;
import com.spacestar.back.block.vo.req.BlockReqVo;

import java.util.List;

public interface BlockService {
    void addBlock(String uuid, BlockReqDto blockReqDto);

    void deleteBlock(String uuid, BlockReqDto blockReqDto);

    BlockExistResDto existBlock(String uuid, String targetUuid);

    List<BlockListResDto> blockList(String uuid);
}
