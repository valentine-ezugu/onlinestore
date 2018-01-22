package com.valentine.service;

import com.valentine.service.config.TestRunner;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@Configuration("com.valentine.repository")
@SpringBootTest(classes = TestRunner.class, properties = "classpath:application.properties")
public abstract class AbstractTest {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

}
