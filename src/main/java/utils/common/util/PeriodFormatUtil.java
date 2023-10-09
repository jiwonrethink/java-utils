package utils.common.util;

import utils.common.dto.period.PeriodDateTimeDto;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PeriodFormatUtil {
    public static PeriodDateTimeDto DAILY() {
        SimpleDateFormat hourFormat = new SimpleDateFormat("yyyyMMddHH00");
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        String startDateTime = hourFormat.format(calendar.getTime());

        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        String endDateTime = hourFormat.format(calendar.getTime());


        return new PeriodDateTimeDto(startDateTime, endDateTime);
    }

    public static PeriodDateTimeDto DAILY(String date, Boolean isPlusHour) {
        SimpleDateFormat hourFormat = new SimpleDateFormat("yyyyMMddHH00");
        Calendar calendar = Calendar.getInstance();

        // 일자 지정
        calendar.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
        calendar.set(Calendar.MONTH, Integer.parseInt(date.substring(4, 6)));
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DATE, Integer.parseInt(date.substring(6)));

        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        if (isPlusHour) calendar.add(Calendar.HOUR, 1);
        String startDateTime = hourFormat.format(calendar.getTime());

        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        if (isPlusHour) calendar.add(Calendar.HOUR, 1);
        String endDateTime = hourFormat.format(calendar.getTime());

        return new PeriodDateTimeDto(startDateTime, endDateTime);
    }

    public static PeriodDateTimeDto MONTHLY() {
        SimpleDateFormat monthFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        String startDateTime = monthFormat.format(calendar.getTime()) + "0000";

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String endDateTime = monthFormat.format(calendar.getTime()) + "0000";

        return new PeriodDateTimeDto(startDateTime, endDateTime);
    }

    public static PeriodDateTimeDto MONTHLY(String date) {
        SimpleDateFormat monthFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();

        // 월 지정
        calendar.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
        calendar.set(Calendar.MONTH, Integer.parseInt(date.substring(4)));
        calendar.add(Calendar.MONTH, -1);

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        String startDateTime = monthFormat.format(calendar.getTime()) + "0000";

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String endDateTime = monthFormat.format(calendar.getTime()) + "0000";

        return new PeriodDateTimeDto(startDateTime, endDateTime);
    }

    public static PeriodDateTimeDto YEARLY() {
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyyMM01");
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMinimum(Calendar.DAY_OF_YEAR));
        String startDateTime = yearFormat.format(calendar.getTime()) + "0000";

        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
        String endDateTime = yearFormat.format(calendar.getTime()) + "0000";

        return new PeriodDateTimeDto(startDateTime, endDateTime);
    }

    public static PeriodDateTimeDto YEARLY(String date) {
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyyMM01");
        Calendar calendar = Calendar.getInstance();

        // 년도 지정
        calendar.set(Calendar.YEAR, Integer.parseInt(date));

        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMinimum(Calendar.DAY_OF_YEAR));
        String startDateTime = yearFormat.format(calendar.getTime()) + "0000";

        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
        String endDateTime = yearFormat.format(calendar.getTime()) + "0000";

        return new PeriodDateTimeDto(startDateTime, endDateTime);
    }
}