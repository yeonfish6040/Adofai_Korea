package com.yeonfish.adofai_korea.Controllers;

import com.yeonfish.adofai_korea.DevController.logger;
import com.yeonfish.adofai_korea.beans.vo.LevelVO;
import com.yeonfish.adofai_korea.services.LevelManageService;
import com.yeonfish.adofai_korea.util.GoogleSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;


@RestController
@RequestMapping("/")
public class MainController {
    private final logger log = new logger();

    @Autowired
    private LevelManageService levelManageService;

    @RequestMapping(value = "Sync", method = RequestMethod.POST)
    public boolean SyncLevels() throws JSONException, GeneralSecurityException, IOException {
        boolean isSuccess = false;

        GoogleSheet googleSheet = new GoogleSheet("1PzLHfWmVWJHrBGnNSsLTsdH0ibdk0hB4MpKHET1nkpU");
        String res = googleSheet.getSheetData("Levels by ID").toString();
        org.springframework.boot.configurationprocessor.json.JSONArray jsonObject = new JSONObject(res).getJSONArray("sheets").getJSONObject(0).getJSONArray("data").getJSONObject(0).getJSONArray("rowData");
        jsonObject.remove(0);

        LevelVO[] levelVO = new LevelVO[jsonObject.length()];
        for (int i=0;i<jsonObject.length();i++) {
            JSONArray obj = jsonObject.getJSONObject(i).getJSONArray("values");
            levelVO[i] = new LevelVO();
            for (int j=0;j<17;j++) {
                switch (j) {
                    case 0:
                        try {
                            levelVO[i].setID(Integer.parseInt(obj.getJSONObject(j).has("formattedValue") ? obj.getJSONObject(j).getString("formattedValue") : ""));
                        }catch (Exception e) {
                            continue;
                        }
                        break;
                    case 1:
                        try {
                            levelVO[i].setSong(obj.getJSONObject(j).has("formattedValue") ? obj.getJSONObject(j).getString("formattedValue") : "");
                        }catch (Exception e) {
                            continue;
                        }
                        break;
                    case 2:
                        try {
                            levelVO[i].setArtist(obj.getJSONObject(j).has("formattedValue") ? obj.getJSONObject(j).getString("formattedValue") : "");
                        }catch (Exception e) {
                            continue;
                        }
                        break;
                    case 3:
                        try {
                            levelVO[i].setDifficulty(obj.getJSONObject(j).has("formattedValue") ? obj.getJSONObject(j).getString("formattedValue") : "");
                        }catch (Exception e) {
                            continue;
                        }
                        break;
                    case 4:
                        try {
                            levelVO[i].setCreator(obj.getJSONObject(j).has("formattedValue") ? obj.getJSONObject(j).getString("formattedValue") : "");
                        }catch (Exception e) {
                            continue;
                        }
                        break;
                    case 5:
                        try {
                            levelVO[i].setDownload(obj.getJSONObject(j).has("hyperlink") ? obj.getJSONObject(j).getString("hyperlink") : "");
                        }catch (Exception e) {
                            continue;
                        }
                        break;
                    case 6:
                        try {
                            levelVO[i].setWorkshop(obj.getJSONObject(j).has("hyperlink") ? obj.getJSONObject(j).getString("hyperlink") : "");
                        }catch (Exception e) {
                            continue;
                        }
                        break;
                    case 7:
                        try {
                            levelVO[i].setVideo(obj.getJSONObject(j).has("hyperlink") ? obj.getJSONObject(j).getString("hyperlink") : "");
                        }catch (Exception e) {
                            continue;
                        }
                        break;
                    case 8:
                        try {
                            levelVO[i].setEW((obj.getJSONObject(j).has("formattedValue") ? obj.getJSONObject(j).getString("formattedValue") : "").equals("O"));
                        }catch (Exception e) {
                            continue;
                        }
                        break;
                    case 9:
                        String[] BPM = (obj.getJSONObject(j).has("formattedValue") ? obj.getJSONObject(j).getString("formattedValue") : "").split(" - ");
                        try {
                            levelVO[i].setBPM_MIN(Float.parseFloat(BPM[0]));
                            if (BPM.length == 2) levelVO[i].setBPM_MAX(Float.parseFloat(BPM[1]));
                            else levelVO[i].setBPM_MAX(Float.parseFloat(BPM[0]));
                        }catch (Exception e) {
                            levelVO[i].setBPM_MIN(0f);
                            levelVO[i].setBPM_MAX(0f);
                        }
                        break;
                    case 10:
                        try {
                            levelVO[i].setTiles(Integer.parseInt(obj.getJSONObject(j).has("formattedValue") ? obj.getJSONObject(j).getString("formattedValue") : ""));
                        }catch (Exception e) {
                            continue;
                        }
                        break;
                    case 11:
                        try {
                            levelVO[i].setTag_1(obj.getJSONObject(j).has("formattedValue") ? obj.getJSONObject(j).getString("formattedValue") : "");
                        }catch (Exception e) {
                            continue;
                        }
                        break;
                    case 12:
                        try {
                            levelVO[i].setTag_2(obj.getJSONObject(j).has("formattedValue") ? obj.getJSONObject(j).getString("formattedValue") : "");
                        }catch (Exception e) {
                            continue;
                        }
                        break;
                    case 13:
                        try {
                            levelVO[i].setTag_3(obj.getJSONObject(j).has("formattedValue") ? obj.getJSONObject(j).getString("formattedValue") : "");
                        }catch (Exception e) {
                            continue;
                        }
                        break;
                    case 14:
                        try {
                            levelVO[i].setTag_4(obj.getJSONObject(j).has("formattedValue") ? obj.getJSONObject(j).getString("formattedValue") : "");
                        }catch (Exception e) {
                            continue;
                        }
                        break;
                    case 15:
                        try {
                            levelVO[i].setTag_5(obj.getJSONObject(j).has("formattedValue") ? obj.getJSONObject(j).getString("formattedValue") : "");
                        }catch (Exception e) {
                            continue;
                        }
                        break;
                }
            }
        }

        isSuccess = levelManageService.SyncLevels(levelVO);

        return isSuccess;
    }
}
