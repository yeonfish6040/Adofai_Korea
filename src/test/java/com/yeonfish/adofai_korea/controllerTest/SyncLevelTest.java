package com.yeonfish.adofai_korea.controllerTest;

import com.yeonfish.adofai_korea.Controllers.MainController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(MainController.class)
public class SyncLevelTest {
    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("SyncLevelTest")
    public void SyncLevelTest() throws Exception {
        mvc.perform(get("/Sync"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}
