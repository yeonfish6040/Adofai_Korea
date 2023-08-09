package com.yeonfish.adofai_korea.services;

import com.yeonfish.adofai_korea.DevController.logger;
import com.yeonfish.adofai_korea.beans.dao.LevelSyncDAO;
import com.yeonfish.adofai_korea.beans.vo.LevelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LevelManageService implements LevelManageServiceInter {
    private final logger log = new logger();

    @Autowired
    private LevelSyncDAO levelSyncDAO;

    @Override
    public boolean SyncLevels(LevelVO[] levels) {
        levelSyncDAO.EmptyLevels();

        int success = 0;
        int fail = 0;
        for (LevelVO levelVO : levels) {
            log.info("[In Progress] SyncLevels: " + success + " success, " + fail + " fail");
            if (!levelSyncDAO.InsertLevels(levelVO)) {
                fail++;
            } else {
                success++;
            }
        }
        log.info("[Finished] SyncLevels: " + success + " success, " + fail + " fail");

        return success == levels.length;
    }
}
