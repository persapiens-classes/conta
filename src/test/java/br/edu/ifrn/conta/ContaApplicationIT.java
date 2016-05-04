package br.edu.ifrn.conta;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = ContaApplication.class)
@WebAppConfiguration
public class ContaApplicationIT extends AbstractTestNGSpringContextTests {

    @Test
    public void contextLoads() {
    }
}
