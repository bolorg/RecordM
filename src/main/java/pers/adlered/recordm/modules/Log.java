package pers.adlered.recordm.modules;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.timeloveboy.moeserver.DefaultHandle;
import com.github.timeloveboy.moeserver.IHttpRequest;
import com.github.timeloveboy.moeserver.IHttpResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Log extends DefaultHandle {

    @Override
    public void POST(IHttpRequest req, IHttpResponse resp) throws Exception {
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
        if (category.equals("statistics")) {
            // 站点统计数据
            System.out.println(System.currentTimeMillis() + " STATISTICS RECV ==> " + postBodyJson.getString("data"));
            result.put("status", 200);
        } else if (category.equals("logErrors")) {
            // 错误信息统计
            System.out.println(System.currentTimeMillis() + " LOGERRORS RECV ==> " + postBodyJson.getString("data"));
            result.put("status", 200);
        } else {
            // 未知日志分类
            result.put("status", 500);
            result.put("message", "Not a match category.");
        }

        resp.write(result.toString());
    }
}
