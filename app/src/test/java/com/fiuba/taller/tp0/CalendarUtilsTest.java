package com.fiuba.taller.tp0;

import com.fiuba.taller.tp0.utils.CalendarUtils;

import junit.framework.Assert;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarUtilsTest {

    @Test
    public void correctlyParsedMarch18() {
        String day = CalendarUtils.getDayOfWeek("2018-03-18", "-");
        String answer = "Domingo, 18/03";
        Assert.assertTrue(answer.equals(day));
    }

    @Test
    public void correctlyParsedFebruary28() {
        String day = CalendarUtils.getDayOfWeek("2018-02-28", "-");
        String answer = "Miercoles, 28/02";
        Assert.assertTrue(answer.equals(day));
    }

    @Test
    public void correctlyParsedTodayDate() {
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        String day = CalendarUtils.getDayOfWeek(dateFormater.format(calendar.getTime()), "-");
        String answer = "Hoy";
        Assert.assertTrue(answer.equals(day));
    }

    @Test
    public void correctlyParsedTomorrowDate() {
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        String day = CalendarUtils.getDayOfWeek(dateFormater.format(calendar.getTime()), "-");
        String answer = "Mañana";
        Assert.assertTrue(answer.equals(day));
    }
}
