package com.spacestar.back.chat.converter;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class ConvertToIndexVo {
    public static <D, V> List<V> convertAndIndex(List<D> dtoList, Class<V> voClass, ModelMapper mapper) {
        return IntStream.range(0, dtoList.size())
                .mapToObj(i -> {
                    D dto = dtoList.get(i);
                    V vo = mapper.map(dto, voClass);
                    try {
                        vo.getClass().getMethod("setIndex", int.class).invoke(vo, i + 1);
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to set index", e);
                    }
                    return vo;
                })
                .collect(Collectors.toList());
    }
}
