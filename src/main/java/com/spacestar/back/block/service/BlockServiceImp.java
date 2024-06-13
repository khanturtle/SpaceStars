package com.spacestar.back.block.service;

import com.spacestar.back.block.domain.Block;
import com.spacestar.back.block.dto.req.BlockReqDto;
import com.spacestar.back.block.dto.res.BlockExistResDto;
import com.spacestar.back.block.dto.res.BlockListResDto;
import com.spacestar.back.block.repository.BlockRepository;
import com.spacestar.back.block.vo.req.BlockReqVo;
import com.spacestar.back.global.GlobalException;
import com.spacestar.back.global.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Transactional
public class BlockServiceImp implements BlockService{

    private final BlockRepository blockRepository;

    // 차단
    @Override
    public void addBlock(String uuid, BlockReqDto blockReqDto) {

        if (blockRepository.existsByUuidAndTargetUuid(uuid, blockReqDto.getTargetUuid())){
            throw new GlobalException(ResponseStatus.ALREADY_BLOCKED);
        }
        blockRepository.save(Block.builder()
                .uuid(uuid)
                .targetUuid(blockReqDto.getTargetUuid())
                .build());
    }

    // 차단 취소
    @Override
    public void deleteBlock(String uuid, BlockReqDto blockReqDto) {

        Optional<Block> block = blockRepository.findByUuidAndTargetUuid(uuid, blockReqDto.getTargetUuid());
        if (block.isEmpty()){
            throw new GlobalException(ResponseStatus.NOT_EXIST_BLOCK);
        }
        blockRepository.delete(block.get());
    }

    // 차단 여부 조회
    @Override
    public BlockExistResDto existBlock(String uuid, String targetUuid) {

        return BlockExistResDto.builder()
                .isExist(blockRepository.existsByUuidAndTargetUuid(uuid, targetUuid))
                .build();
    }

    // 차단 목록 조회
    @Override
    public List<BlockListResDto> blockList(String uuid) {

        List<Block> blockList = blockRepository.findAllByUuid(uuid);

        return IntStream.range(0, blockList.size())
                .mapToObj(index -> BlockListResDto.convertToDto(index, blockList.get(index)))
                .toList();

    }
}
