package com.fiuba.taller.tp0.utils;

import java.util.Calendar;

public class CalendarUtils {

    private static final String LOG_TAG = "CalendarUtils";

    private static final String DAY_FORMAT = "%s, %02d/%02d";

    public static String getDayOfWeek(String yearMonthDayDate, String separator) {
        int todayDayOfWeek = getTodayDayOfWeek();

        String[] parsedDate = yearMonthDayDate.split(separator);
        int year = Integer.parseInt(parsedDate[0]);
        int month = Integer.parseInt(parsedDate[1]);
        int day = Integer.parseInt(parsedDate[2]);
        int dateDayOfWeek = getDateDayOfWeek(day, month, year);

        if (todayDayOfWeek == dateDayOfWeek) {
            return "Hoy";
        }
        int tomorrow = getTomorrowDayOfWeek(todayDayOfWeek);
        if (dateDayOfWeek == tomorrow) {
            return "Mañana";
        }

        return dayOfWeakToString(dateDayOfWeek, day, month);
    }

    private static int getTodayDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    private static int getDateDayOfWeek(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    private static int getTomorrowDayOfWeek(int todayDayOfWeek) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    private static String dayOfWeakToString(int dayCode, int day, int month) {

        switch (dayCode) {
            case Calendar.SUNDAY: return String.format(DAY_FORMAT, "Domingo", day, month);
            case Calendar.MONDAY: return String.format(DAY_FORMAT, "Lunes", day, month);
            case Calendar.TUESDAY: return String.format(DAY_FORMAT, "Martes", day, month);
            case Calendar.WEDNESDAY: return String.format(DAY_FORMAT, "Miercoles", day, month);
            case Calendar.THURSDAY: return String.format(DAY_FORMAT, "Jueves", day, month);
            case Calendar.FRIDAY: return String.format(DAY_FORMAT, "Viernes", day, month);
            case Calendar.SATURDAY: return String.format(DAY_FORMAT, "Sabado", day, month);
            default: return "";
        }
    }
}
