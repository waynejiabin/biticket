package com.biticket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 启动程序
 * 
 * @author biticket
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@MapperScan("com.biticket.project.*.*.mapper")
public class BiticketApplication
{
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(BiticketApplication.class, args);
        System.out.println("     \n" +
                " .-------.-------.-------.-------.  \n" +
                " |                               |   \n" +
                " |         系统启动成功          |   \n" +
                " |                               |   \n" +
                " .-------.-------.-------.-------.     ");
    }
}