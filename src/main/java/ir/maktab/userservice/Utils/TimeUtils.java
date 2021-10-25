package ir.maktab.userservice.Utils;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeUtils {
    public static Date dateOf(String date) throws ParseException {
        java.util.Date utilDate = new SimpleDateFormat("dd MM yyyy").parse(date);
        return new Date(utilDate.getTime());

        ///     @Test
        ///    public void timeUtilTest() throws ParseException {
        ///        Date date = TimeUtils.parseDate("11 11 2020");
        ///        System.out.println(date.getYear());
        ///
        ///    }
    }

    public static Time

    timeOf(String time) {
       return java.sql.Time.valueOf(time);

        ///    @Test
        ///    public void hourTest() {
        ///        Time time = Time.valueOf("10:20:00");
        ///        System.out.println(time.getHours());
        ///
        ///    }

    }
}
