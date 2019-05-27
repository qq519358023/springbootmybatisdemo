package com.beijia.example;

import com.beijia.example.util.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringmybatisApplicationTests {

    @Autowired
    RedisService redisService;

    @Test
    public void findAllUsers()  {
        redisService.listPush("list1","hello");
        redisService.listPush("list1","1");
    }


    @Test
    public void findAllUsers2()  {
        System.out.println("get key value:"+ redisService.listFindAll("list1 "));
    }

}
