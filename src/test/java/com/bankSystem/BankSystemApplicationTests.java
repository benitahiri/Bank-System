package com.bankSystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BankSystemApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
        assertNotNull(context);  // Checks if the application context loads successfully
        assertTrue(context.getBeanDefinitionCount() > 0);  // Ensures some beans are configured
    }

    @Test
    void testServiceBeanPresence() {
        // Check for the presence of a critical service or component
        assertNotNull(context.getBean("accountService"), "Account Service should be present");
    }
}
