package com.yeonfish.adofai_korea.mappers;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TimeMapper {
    //@Select("select sysdate from dual")
    public String getTime();
}
