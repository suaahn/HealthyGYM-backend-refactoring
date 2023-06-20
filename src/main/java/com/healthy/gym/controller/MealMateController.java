package com.healthy.gym.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthy.gym.dto.BbsDto;
import com.healthy.gym.dto.BbsParam;
import com.healthy.gym.dto.FoodDto;
import com.healthy.gym.service.MealMateService;
import com.healthy.gym.utils.Utility;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mate/meal")
public class MealMateController {

    @Autowired
    MealMateService service;

    @GetMapping("/posts")
    public List<Map<String, Object>> getMealMatePostList(int memberseq, int page, int limit, String select) {
        Map<String, Object> param = new HashMap<>();
        param.put("offset", page * limit);
        param.put("limit", limit);
        param.put("memberseq", memberseq);
        param.put("select", select);

        return service.getMealMatePostList(param);
    }

    @GetMapping("/foods")
    public Map<String, Object> getFoodList(String search, int pageNo) throws IOException {
        Map<String, Object> result = new HashMap<>();

        // 공공데이터 api - 식품 영양성분 정보
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/FoodNtrIrdntInfoService1/getFoodNtrItdntList1"); //URL
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=aod5N32JX4OSOPRMyQPTw8VUZxR9zvbrkDLsrM%2BdNRtHP%2BuVlr31Np4fWwVVYm131hApK%2FB4auwWMKzrMmqMhg%3D%3D"); //Service Key
        urlBuilder.append("&" + URLEncoder.encode("desc_kor","UTF-8") + "=" + URLEncoder.encode(search, "UTF-8")); //식품이름
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(String.valueOf(pageNo), "UTF-8")); //페이지번호

        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); //한 페이지 결과 수
        urlBuilder.append("&" + URLEncoder.encode("bgn_year","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); //구축년도
        urlBuilder.append("&" + URLEncoder.encode("animal_plant","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); //가공업체
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); //응답데이터 형식(xml/json) Default: xml

        // GET
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        //System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        String jsonString = sb.toString(); // replace with actual JSON string
        //System.out.println(jsonString);

        JSONObject jsonObject = new JSONObject(jsonString);
        JSONObject body = jsonObject.getJSONObject("body");

        int totalCount = body.getInt("totalCount");

        List<FoodDto> list = new ArrayList<>();

        if(body.has("items")) {
            JSONArray items = body.getJSONArray("items");

            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                FoodDto dto = new FoodDto(item.getString("DESC_KOR"), item.getString("SERVING_WT"),
                        item.getString("NUTR_CONT1"), item.getString("NUTR_CONT2"), item.getString("NUTR_CONT3"),
                        item.getString("NUTR_CONT4"), item.getString("NUTR_CONT5"), item.getString("NUTR_CONT6"),
                        item.getString("NUTR_CONT7"), item.getString("NUTR_CONT8"), item.getString("NUTR_CONT9"),
                        item.getString("BGN_YEAR"), item.getString("ANIMAL_PLANT"));
                list.add(dto);
            }
        }

        result.put("foodList", list);
        result.put("totalCount", totalCount);
        return result;
    }

    @PostMapping("/write")
    public boolean writeMealMatePost(@RequestBody BbsDto bbsdto,
                                     @RequestBody String selectedItems) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<FoodDto> list = objectMapper.readValue(selectedItems, objectMapper.getTypeFactory().constructCollectionType(List.class, FoodDto.class));

        return service.writeMealMatePost(bbsdto, list);
    }
}
