package com.github.mikenussbaumer.coronavirus_tracker.databasemanager.entity;

import lombok.*;

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
public class Country {

    private int id;
    private String name;
    private double latitude;
    private double longitude;

}
