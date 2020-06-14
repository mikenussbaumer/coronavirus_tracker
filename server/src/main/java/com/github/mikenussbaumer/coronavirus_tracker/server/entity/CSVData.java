package com.github.mikenussbaumer.coronavirus_tracker.server.entity;

import lombok.*;

import java.util.Date;
import java.util.HashMap;

/**
 * @author Mike Nußbaumer (dev@mike-nussbaumer.com)
 * @date 23.04.20
 * @time 20:04
 * @project coronavirus-tracker
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CSVData {

    private String country;
    private double latitude;
    private double longitude;

    HashMap < Date, Integer > confirmed_cases;
}
