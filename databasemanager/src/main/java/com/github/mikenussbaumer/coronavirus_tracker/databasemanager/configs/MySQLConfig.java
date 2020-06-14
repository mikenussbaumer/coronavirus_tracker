package com.github.mikenussbaumer.coronavirus_tracker.databasemanager.configs;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Mike Nußbaumer (dev@mike-nussbaumer.com)
 * @date 14.06.20
 * @time 11:26
 * @project coronavirus-tracker
 */
public class MySQLConfig {

    @Getter
    @Setter
    private String hostname = "localhost";

    @Getter
    @Setter
    private String username = "sa";

    @Getter
    @Setter
    private String password = "Pa55w0rd";

    @Getter
    @Setter
    private String database = "coronavirus_tracker";

    @Getter
    @Setter
    private int port = 3306;
}
