package com.spacestar.back.block.service;

import com.spacestar.back.block.domain.Block;
import com.spacestar.back.block.dto.req.BlockReqDto;
import com.spacestar.back.block.repository.BlockRepository;
import com.spacestar.back.block.vo.req.BlockReqVo;
import com.spacestar.back.global.GlobalException;
import com.spacestar.back.global.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BlockServiceImp implements BlockService{

    private final BlockRepository blockRepository;

    @Transactional
    @Override
    public void addBlock(String uuid, BlockReqDto blockReqDto) {

        if (blockRepository.existsByUuidAndTargetUuid(uuid, blockReqDto.getTargetUuid())){
            throw new GlobalException(ResponseStatus.INTERNAL_SERVER_ERROR);
        }
        blockRepository.save(Block.builder()
                .uuid(uuid)
                .targetUuid(blockReqDto.getTargetUuid())
                .build());
    }
}
