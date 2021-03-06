package com.baizhi;

import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.ArticleDao;
import com.baizhi.dao.MenuDao;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.Album;
import com.baizhi.service.ArticleService;
import com.baizhi.service.BannerService;
import io.goeasy.GoEasy;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzXshApplicationTests {

    @Autowired
    MenuDao menuDao;

    @Autowired
    BannerService bannerService;

    @Autowired
    AlbumDao albumDao;
    @Autowired
    ArticleDao articleDao;

    @Autowired
    ArticleService articleService;

    @Autowired
    UserDao userDao;


    @Test
    public void contextLoads() {
        List<Album> albums = albumDao.selectAllChapter();
        System.out.println(albums);
    }

    @Test
    public void test1() {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);

    }

    @Test
    public void test22() {
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-1f3b41fa7a3647429e5657d9cfb2fd2b");

        goEasy.publish("xsh", "Hello, GoEasy!");


    }


    @Test
    public void testMD5() {

        String uuid = UUID.randomUUID().toString().replace("-", "").substring(3, 6);

        String d1 = DigestUtils.md5Hex("123456");

        String s1 = d1 + uuid;

        String s = DigestUtils.md5Hex(s1);

        System.out.println(s);


    }
/*

    @Test
    public void testMessage() throws ClientException {
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        //替换成你的AK
        final String accessKeyId = "LTAIgMBmSXEZn92j";//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = "3ybLrjCrt3O2tRbRe5L60xOVgyeWuA";//你的accessKeySecret，参考本文档步骤2
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
        request.setPhoneNumbers("15515546135");
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("艾小飞鱼");
        //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
        request.setTemplateCode("SMS_164100021");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{\"code\":\"888888\"}");
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
//请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
//请求成功
        }
    }
*/



//    @Test
//    public void login(){
//        User user = new User();
//        user.setName("大龙");
//        user.setPassword("111111");
//        User user1 = userDao.selectByName(user);
//        System.out.println(user1);
//
//    }



}


