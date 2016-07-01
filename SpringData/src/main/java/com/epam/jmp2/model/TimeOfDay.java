package com.epam.jmp2.model;

import java.time.LocalTime;

public class TimeOfDay {

    public enum TimeCategory{
        MORNING, AFTERNOON, EVENING, NIGHT, INVALID_TIME;
    }

    public static String getTimeOfDay(LocalTime localtime){
        if (localtime.getHour() < 6) return TimeCategory.NIGHT.toString();
        else if (localtime.getHour() < 12) return TimeCategory.MORNING.toString();
        else if (localtime.getHour() < 18) return TimeCategory.AFTERNOON.toString();
        else if (localtime.getHour() < 24)  return TimeCategory.EVENING.toString();
        else return TimeCategory.INVALID_TIME.toString();

    }
}