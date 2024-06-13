package com.spacestar.back.block.dto.res;

import com.spacestar.back.block.domain.Block;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlockListResDto {

    private int index;
    private String blockUuid;



    public static BlockListResDto convertToDto(int index, Block block) {
        return BlockListResDto.builder()
                .index(index)
                .blockUuid(block.getTargetUuid())
                .build();
    }
}
