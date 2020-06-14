package com.github.mikenussbaumer.coronavirus_tracker.databasemanager.entity;

import lombok.*;

import java.util.Date;

/**
 * @author Mike Nußbaumer (dev@mike-nussbaumer.com)
 * @date 22.04.20
 * @time 15:08
 * @project coronavirus-tracker
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CoronaData {

    private int id;
    private Date date;
    private int country_id;
    private int confirmed_cases;

}
