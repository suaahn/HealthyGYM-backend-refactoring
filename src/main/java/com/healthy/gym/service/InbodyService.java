package com.healthy.gym.service;

import com.healthy.gym.dao.InbodyDao;
import com.healthy.gym.dto.InbodyDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class InbodyService {
    @Autowired
    InbodyDao dao;

    @Value("${Naver.OCR.url}")
    private String apiURL;
    @Value("${Naver.OCR.secret}")
    private String secretKey;

    public List<InbodyDto> getInbodyList(int memberseq) {
        return dao.getInbodyList(memberseq);
    }

    // 인바디 OCR 처리 & 인식 결과 DB에 저장
    public InbodyDto ocr(int memberseq, String filepath, String filename) {

        InbodyDto result = new InbodyDto();

        String imageFile = filepath;

        StringBuffer response = null;
        try {
            // 커넥션 설정
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setReadTimeout(30000);
            con.setRequestMethod("POST");
            String boundary = "----" + UUID.randomUUID().toString().replaceAll("-", "");
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            con.setRequestProperty("X-OCR-SECRET", secretKey);

            // 요청 파라미터
            JSONObject json = new JSONObject();
            json.put("version", "V2");
            json.put("requestId", UUID.randomUUID().toString());
            json.put("timestamp", System.currentTimeMillis());
            JSONObject image = new JSONObject();
            image.put("format", "jpg");
            image.put("name", "demo");
            JSONArray images = new JSONArray();
            images.put(image);
            json.put("images", images);
            String postParams = json.toString();

            con.connect();
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            long start = System.currentTimeMillis();
            File file = new File(imageFile);

            writeMultiPart(wr, postParams, file, boundary);
            wr.close();

            // 응답
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            String str = response.toString();   // OCR결과를 문자열로 저장

            List<String> ocrList = new ArrayList<>();

            String[] lines = str.split("\\\\n"); // 개행을 기준으로 문자열을 나눔
            for (String line : lines) {
                String[] words = line.split("\\s+"); // 띄어쓰기를 기준으로 단어를 나눔
                Collections.addAll(ocrList, words); // 나눈 단어를 리스트에 추가함
            }

            int k = 0;
            int index[] = {0, 0, 0};
            ocrList.add(0, "이미지 인식 실패"); // 아래의 for문에서 값을 찾지 못했을 때 출력할 정보

            String field[] = {"골격근량", "체지방량", "비만분석"};  // Search 대상 다음으로 나오는 텍스트
            for (int i = 1; i < ocrList.size(); i++) {
                if (ocrList.get(i).equals(field[k])) {
                    index[k] = i - 1;
                    k++;
                    if (k == field.length) {
                        break;
                    }
                }
            }

            // Dao로 넘길 값
            System.out.println("체중: " + ocrList.get(index[0])
                            + " 골격근량: " + ocrList.get(index[1])
                            + " 체지방량: " + ocrList.get(index[2]));

            double weight = Double.parseDouble(ocrList.get(index[0]));
            double musclemass = Double.parseDouble(ocrList.get(index[1]));
            double bodyfatmass = Double.parseDouble(ocrList.get(index[2]));

            InbodyDto inbodyDto = new InbodyDto(memberseq, weight, musclemass, bodyfatmass, filename);

            result = inbodyDto;

            dao.insertInbody(inbodyDto);

        } catch (Exception e) {
            System.out.println(e);
            return result;
        }

        return result;
    }

    private void writeMultiPart(OutputStream out, String jsonMessage, File file, String boundary) throws
            IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("--").append(boundary).append("\r\n");
        sb.append("Content-Disposition:form-data; name=\"message\"\r\n\r\n");
        sb.append(jsonMessage);
        sb.append("\r\n");

        out.write(sb.toString().getBytes("UTF-8"));
        out.flush();

        if (file != null && file.isFile()) {
            out.write(("--" + boundary + "\r\n").getBytes("UTF-8"));
            StringBuilder fileString = new StringBuilder();
            fileString
                    .append("Content-Disposition:form-data; name=\"file\"; filename=");
            fileString.append("\"" + file.getName() + "\"\r\n");
            fileString.append("Content-Type: application/octet-stream\r\n\r\n");
            out.write(fileString.toString().getBytes("UTF-8"));
            out.flush();

            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] buffer = new byte[8192];
                int count;
                while ((count = fis.read(buffer)) != -1) {
                    out.write(buffer, 0, count);
                }
                out.write("\r\n".getBytes());
            }

            out.write(("--" + boundary + "--\r\n").getBytes("UTF-8"));
        }
        out.flush();
    }

    public boolean saveInbody(int inbodyseq) {
        return dao.saveInbody(inbodyseq) > 0;
    }

    public boolean deleteInbody(int inbodyseq) {
        return dao.deleteInbody(inbodyseq) > 0;
    }
}
