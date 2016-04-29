package br.edu.ifrn.conta;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ContaApplication {
    
    public static void main(String[] args) {
        new SpringApplicationBuilder()                
                .sources(ContaApplication.class)
                .run(args);
    }
}
