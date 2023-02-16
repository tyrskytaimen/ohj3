import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import static java.time.temporal.ChronoUnit.DAYS;

public class Dates {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);

    public static class DateDiff {
        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
        private final String start;
        private final String end;
        private final int diff;

        private DateDiff(String start, String end) {
            this.start = start;
            this.end = end;
            this.diff = (int) LocalDate.parse(start).until(LocalDate.parse(end), DAYS);
        }

        public String getStart() {
            return LocalDate.parse(this.start).format(DateTimeFormatter.ISO_DATE);
        }

        public String getEnd() {
            return LocalDate.parse(this.end).format(DateTimeFormatter.ISO_DATE);
        }

        public int getDiff() {
            return this.diff;
        }

        @Override
        public String toString() {
            /*DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("E dd.MM.yyyy", Locale.US);
            String s1 = String.valueOf(LocalDate.parse(start, formatter2));
            String s2 = String.valueOf(LocalDate.parse(end, formatter2));
            return s1 + " --> " + s2 + ": " + diff + " days";*/

            LocalDate dateStart = LocalDate.parse(this.start);
            LocalDate dateEnd = LocalDate.parse(this.end);

            String aloituspaiva = dateStart.getDayOfWeek().toString();
            aloituspaiva = aloituspaiva.substring(0,1).toUpperCase()+aloituspaiva.substring(1).toLowerCase();
            String lopetuspaiva = dateEnd.getDayOfWeek().toString();
            lopetuspaiva = lopetuspaiva.substring(0,1).toUpperCase()+lopetuspaiva.substring(1).toLowerCase();

            String aloitus = dateStart.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            String lopetus = dateEnd.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

            String returni;
            if(this.diff!=1) {
                returni = String.format("%s %s --> %s %s: %d days",
                        aloituspaiva, aloitus, lopetuspaiva, lopetus, this.diff);
            } else {
                returni = String.format("%s %s --> %s %s: %d day",
                        aloituspaiva, aloitus, lopetuspaiva, lopetus, this.diff);
            }
            return returni;
        }
    }

    private Dates() {
    }
    public static DateDiff[] dateDiffs(String ...dateStrs) throws DateTimeException {
        ArrayList<LocalDate> days = new ArrayList<>();
        ArrayList<DateDiff> returni = new ArrayList<>();
        for(String day : dateStrs){
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            SimpleDateFormat correctFormat = new SimpleDateFormat("yyyy-MM-dd");
            format.setLenient(false);
            try{
                java.util.Date x = format.parse(day);
                LocalDate y = LocalDate.parse(correctFormat.format(x));

                if(y.getYear()<1000 || y.getYear()>9999){
                    System.out.printf("The date \"%s\" is illegal!%n",day);
                    continue;
                }

                days.add(y);
            } catch (ParseException e1) {
                try {
                    LocalDate x = LocalDate.parse(day);

                    if(x.getYear()<1000||x.getYear()>9999){
                        System.out.printf("The date \"%s\" is illegal!%n",day);
                        continue;
                    }

                    days.add(x);
                } catch (DateTimeParseException e2) {
                    System.out.printf("The date \"%s\" is illegal!%n",day);
                }
            }
        }
        if(days.size()>=2){
            Collections.sort(days);
            for(int x = 0;x<days.size()-1;x++){
                returni.add(new DateDiff(days.get(x).toString(),days.get(x+1).toString()));
            }

            DateDiff[] xd = {};
            xd = returni.toArray(xd);
            return xd;
        } else {
            return new DateDiff[0];
        }
    }
}
