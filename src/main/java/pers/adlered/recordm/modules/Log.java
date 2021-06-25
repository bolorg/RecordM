package pers.adlered.recordm.modules;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.timeloveboy.moeserver.DefaultHandle;
import com.github.timeloveboy.moeserver.IHttpRequest;
import com.github.timeloveboy.moeserver.IHttpResponse;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class Log extends DefaultHandle {

    @Override
    public void POST(IHttpRequest req, IHttpResponse resp) {
        try {
            JSONObject result = new JSONObject();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getBody()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            String postBody = stringBuilder.toString();
            JSONObject postBodyJson = JSON.parseObject(postBody);
            String category = postBodyJson.getString("category");
            System.out.println("=================");
            if (category.equals("statistics")) {
                // 站点统计数据
                long serverTime = postBodyJson.getJSONObject("data").getLong("serverTime");
                long delay = System.currentTimeMillis() - serverTime;
                System.out.println(delay + "ms S ==> " + postBodyJson.getString("data"));
                result.put("status", 200);
            } else if (category.equals("logErrors")) {
                String data = postBodyJson.getString("data");
                // 错误信息统计
                System.out.println(System.currentTimeMillis() + " L ==> " + data);
                result.put("status", 200);
            } else {
                // 未知日志分类
                result.put("status", 500);
                result.put("message", "Not a match category.");
            }

            resp.code(200);
            resp.write(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
