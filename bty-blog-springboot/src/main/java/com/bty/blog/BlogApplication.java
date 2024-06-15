package com.bty.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bty.blog.dao")
public class BlogApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(
                        "  ____                                _ \n" +
                        " / ___| _   _  ___ ___ ___  ___ ___  | |\n" +
                        " \\___ \\| | | |/ __/ __/ _ \\/ __/ __| | |\n" +
                        "  ___) | |_| | (_| (_|  __/\\__ \\__ \\ |_|\n" +
                        " |____/ \\__,_|\\___\\___\\___||___/___/ (_)\n" +
                        "                                        "
        );
    }
}
