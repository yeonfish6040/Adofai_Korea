package com.yeonfish.adofai_korea.beans.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LevelVO {
    int ID;
    String Song;
    String Artist;
    String Difficulty;
    String Creator;
    float BPM_MIN;
    float BPM_MAX;
    int Tiles;
    String Tag_1;
    String Tag_2;
    String Tag_3;
    String Tag_4;
    String Tag_5;
    String Download;
    String Workshop;
    String Video;
    boolean EW;
}
