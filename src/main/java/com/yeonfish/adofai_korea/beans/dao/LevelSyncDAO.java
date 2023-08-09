package com.yeonfish.adofai_korea.beans.dao;

import com.yeonfish.adofai_korea.DevController.logger;
import com.yeonfish.adofai_korea.beans.vo.LevelVO;
import com.yeonfish.adofai_korea.mappers.SyncMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LevelSyncDAO {
    private final logger log = new logger();

    @Autowired
    private SyncMapper syncMapper;

    public void EmptyLevels() {
        syncMapper.emptyLevels();
    }

    public boolean InsertLevels(LevelVO levelVO) {
        return syncMapper.insertLevels(levelVO);
    }
}
