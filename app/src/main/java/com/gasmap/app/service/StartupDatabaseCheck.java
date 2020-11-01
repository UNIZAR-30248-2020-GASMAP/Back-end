package com.gasmap.app.service;

import org.springframework.jdbc.core.JdbcTemplate;

public interface StartupDatabaseCheck {
    public void StartupDatabaseCheck(JdbcTemplate template);
    public void init();
}
