package com.yeonfish.adofai_korea.dbTest;

import com.yeonfish.adofai_korea.DevController.logger;
import com.yeonfish.adofai_korea.mappers.TimeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TimeMapperTest {
    private logger log = new logger(true, true);


    @Autowired
    private TimeMapper timeMapper;

    @Test
    public void testGetTime() {
        log.info(timeMapper.getTime());
    }
}
