package com.kiibos.micoservice.kiibos_3_resttemplate.resttemplate;

import com.kiibos.micoservice.kiibos_3_resttemplate.Kiibos3ResttemplateApplicationTests;
import com.kiibos.micoservice.kiibos_3_resttemplate.controller.common.Wrapper;
import com.kiibos.micoservice.kiibos_3_resttemplate.model.query.UserQuery;
import com.kiibos.micoservice.kiibos_3_resttemplate.model.vo.UserVO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName RestTemplateTest
 * @Description TODO
 * @Author cl
 * @Date 2019/3/1 下午10:38
 **/
public class RestTemplateTest extends Kiibos3ResttemplateApplicationTests {

    @Autowired
    private RestTemplate restTemplate;


    @Test
    public void test(){
        String url = "http://localhost:8080/user/1";
        HttpMethod method = HttpMethod.GET;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type","application/json;charset=utf-8");
        HttpEntity httpEntity = new HttpEntity(headers);
        Wrapper<UserVO> wrapper = restTemplate.exchange(url,method,httpEntity,Wrapper.class).getBody();
        System.out.println(wrapper.toString());
    }

}
