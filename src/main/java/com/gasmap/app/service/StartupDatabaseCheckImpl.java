package com.gasmap.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import org.springframework.core.io.Resource;
import org.springframework.test.context.jdbc.Sql;

import javax.annotation.Resources;
import java.io.IOException;

@Service
public class StartupDatabaseCheckImpl implements StartupDatabaseCheck{
    private JdbcTemplate template;

    //@Value("classpath:/resources/data.sql")
    //Resource sqlFile;

    @Autowired
    public void StartupDatabaseCheck(JdbcTemplate template){
        this.template = template;
    }

    @PostConstruct
    //@Sql("app/src/main/resources/data.sql")
    public void init() {
        //log.info("Running custom fields table creation (if required)");
        //String migrateSql = sqlFile.toString();
        //template.execute(sqlFile.toString());

    }
}
