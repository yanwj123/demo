package com.example.demo.controller;

import com.example.demo.entity.Poj;
import com.example.demo.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Controller
public class HelloWord {
    @Autowired
    private Poj poj;

//    Map<String, String> map = Collections.synchronizedMap(new HashMap<>());

    @DeleteMapping(value = "/user/{id}")
//    @ApiOperation(value="更新⽤户详细信息", notes="根据url的id来指定更新对象，并根据传过来的 user信息来更新⽤户详细信息")
    @ApiImplicitParam(name = "id", value = "⽤户ID", required = true, dataType = "int")
    public String putUser(@PathVariable("id") Integer id) throws MyException{
        throw new MyException("111");
//        System.out.println(id);
    }

    @GetMapping(value = "/hello")
    @ApiOperation(value = "hello方法",notes = "初始的方法")
    public String hello(ModelMap map){
        String str = "//pic-centanet-com.oss-cn-beijing.aliyuncs.com/other/yanglao/testpictoossff.jpg?x-oss-process=style/master";
        String imageFormat = "180x130";
        str = imgSizeChange(str,imageFormat);
        map.put("host", "http://pic-centanet-com.oss-cn-beijing.aliyuncs.com/other/yanglao/testpictoossff.jpg?x-oss-process=style/master");
//        System.out.println(map);
//        System.out.println(poj.getVersion());
        System.out.println(str);
        return "index";
    }
    public String imgSizeChange(String url, String imageFormat){
        if (!StringUtils.isEmpty(url)){
            url = url.substring(0, url.lastIndexOf("/")+1) + imageFormat;
            System.out.println(url);
        }
        return url;
    }


    @PutMapping("/abc")
    public String hello1()throws Exception{
        throw new Exception("发生错误");
    }

    @PostMapping("/json")
    public String json() throws MyException{
        throw new MyException("发生了自定义异常");
    }

    @ApiOperation(value = "接受XML格式的方法", notes = "响应XML格式的方法")
    @PostMapping(value = "/createUser", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public User createUser(@RequestBody User user){
        user.setName("abcdefg" + user.getName());
        user.setAge(user.getAge() + 100);
        return user;
    }
/**
 * 图片迁移OSS-提交方法
 * */
    @ApiOperation(value="图片提交公共方法", notes="选定图片提交")
    @PostMapping("/img")
    public void img() throws IOException {
        // 0、 入参准备
        String userName = "j9GBhyAsDkCz";   // 问涛哥要匹配自己系统的参数
        String password = "6dRpk4N1F8hWNH9b";   // 问涛哥要匹配自己系统的参数
        String filePath = "other/yanglao/"; // 问涛哥要匹配自己系统的参数
        String underGroundService = "http://10.4.18.227:7000/api/uploadfiles";  // 上线当天问涛哥确认路径无误
//        File file = new File("C:\\Users\\wbzhaomeng.CENTALINE\\Desktop\\bg.jpg");   // 上传的文件
//        File file = new File("C:\\Users\\wbyanwj\\Desktop\\testpictoossff.jpg");   // 上传的文件
        File file = new File("C:\\Users\\wbyanwj\\AppData\\Local\\Temp\\tomcat.6823960996373428599.8080\\work\\Tomcat\\localhost\\yuyan\\upload_2a8e8c12_2ce7_4f04_8692_fb5a040fec9b_00000016.tmp");   // 上传的文件

        // 1、 初始化请求
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(underGroundService);

        // 2、 header + 参数
        String ascii = new String(String.format("%s:%s",userName,password).getBytes(), StandardCharsets.US_ASCII);
        String base64 = Base64.getEncoder().encodeToString(ascii.getBytes());
        String authorization = "Basic" + " " + base64;
        httpPost.addHeader("Authorization",authorization);
        httpPost.addHeader("BucketName","pic-centanet-com");


        // 2、 body form-data + 参数
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.addPart("filepath",new StringBody(filePath, ContentType.APPLICATION_FORM_URLENCODED));
        multipartEntityBuilder.addPart("formfiles", new FileBody(file));
        httpPost.setEntity(multipartEntityBuilder.build());

        // 3、 发送请求
        HttpResponse httpResponse = httpClient.execute(httpPost);

        // 4、 处理结果
        HttpEntity httpEntity =  httpResponse.getEntity();
        String content = EntityUtils.toString(httpEntity);
        System.err.println(content);
        System.out.println("111");
        return;
    }

//        fileBody.getAbsolutePath()
//        fileBody.getAbsoluteFile()
//                fileBody.getName()

    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }



}
