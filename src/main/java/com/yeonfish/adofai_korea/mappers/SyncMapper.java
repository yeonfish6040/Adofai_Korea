package com.yeonfish.adofai_korea.mappers;

import com.yeonfish.adofai_korea.beans.vo.LevelVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SyncMapper {
    public boolean emptyLevels();
    public boolean insertLevels(LevelVO levelVO);
}
