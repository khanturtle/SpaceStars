package com.spacestar.back.block.repository;

import com.spacestar.back.block.domain.Block;
import com.spacestar.back.block.dto.req.BlockReqDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<Block, Long> {
    boolean existsByUuidAndTargetUuid(String uuid, String targetUuid);
}
