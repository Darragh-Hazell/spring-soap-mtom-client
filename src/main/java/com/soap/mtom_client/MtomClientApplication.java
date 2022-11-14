package com.soap.mtom_client;

import com.soap.mtom_client.wsdl.SendStuffResponse;
import com.soap.mtom_client.wsdl.Stuff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@SpringBootApplication
public class MtomClientApplication {

    private static final Logger LOG = LoggerFactory.getLogger(MtomClientApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MtomClientApplication.class, args);
    }

    @Bean
    CommandLineRunner lookup(StuffClient client) {
        return args -> {

            Path pathToZip = Paths.get("/Users/darragh/Documents/Spring/mtom_client/src/main/resources/lmao.txt");
            byte[] data = Files.readAllBytes(pathToZip);
            byte[] sauce = Base64.getEncoder().encode(data);

            Stuff stuff = new Stuff();
            stuff.setName("This is stuff");
            stuff.setSauce(sauce);

            SendStuffResponse response = client.sendStuff(stuff);

            LOG.info(response.toString());
            LOG.info(String.valueOf(response.isSuccess()));
        };
    }
}
