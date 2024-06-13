package com.spacestar.back.block.repository;

import com.spacestar.back.block.domain.Block;
import com.spacestar.back.block.dto.req.BlockReqDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlockRepository extends JpaRepository<Block, Long> {
    boolean existsByUuidAndTargetUuid(String uuid, String targetUuid);

    Optional<Block> findByUuidAndTargetUuid(String uuid, String targetUuid);

    List<Block> findAllByUuid(String uuid);
}
