package com.yeonfish.adofai_korea.services;

import com.yeonfish.adofai_korea.beans.vo.LevelVO;
import org.springframework.stereotype.Service;

@Service
public interface LevelManageServiceInter {
    public boolean SyncLevels(LevelVO[] levels);
}
