package com.company.project.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class httpUtil {

    public static String httpURLConectionGET(String GET_URL) {
        try {
            URL url = new URL(GET_URL);    // 把字符串转换为URL请求地址
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 打开连接
            connection.connect();// 连接会话
            // 获取输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {// 循环读取流
                sb.append(line);
            }
            br.close();// 关闭流
            connection.disconnect();// 断开连接
            System.out.println(sb.toString());
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败!");
            return null;
        }
    }

    public static void main(String[] args) {
        httpURLConectionGET("https://api.weixin.qq.com/sns/jscode2session?appid=wx2f5d3f7e449fea6a&secret=daaee00c761b45d5505b38a01f88302d&js_code=021WGmIE136I120gQIHE16MwIE1WGmIE&grant_type=authorization_code");
//        httpURLConnectionPOST();
    }
}
