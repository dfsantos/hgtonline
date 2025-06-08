package com.dfsantos.hgtonline;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HgtonlineApplicationTests {

    @Autowired
    private Environment environment;

    @Test
    void contextLoads() {
        assertEquals("hgtonline", environment.getProperty("spring.application.name"));
    }
}
