package com.spacestar.back.block.service;

import com.spacestar.back.block.dto.req.BlockReqDto;
import com.spacestar.back.block.vo.req.BlockReqVo;

public interface BlockService {
    void addBlock(String uuid, BlockReqDto blockReqDto);
}
