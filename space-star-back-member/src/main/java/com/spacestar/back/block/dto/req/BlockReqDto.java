package com.spacestar.back.block.dto.req;

import com.spacestar.back.block.domain.Block;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlockReqDto {

    private String targetUuid;

}
