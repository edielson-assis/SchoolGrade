package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.enums.DaysOfTheWeek;
import model.exceptions.DomainException;

public class SchoolGrade {
    
    private String classes;
    private Date initialHour;
    private Date lastHour;
    private DaysOfTheWeek daysOfTheWeek;

    private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    public SchoolGrade() {

    }

    public SchoolGrade(String classes, Date initialHour, Date lastHour, DaysOfTheWeek daysOfTheWeek) {
        if (!lastHour.after(initialHour)) {
			throw new DomainException("Error : last hour must be after initial hour");
		}
        this.classes = classes;
        this.initialHour = initialHour;
        this.lastHour = lastHour;
        this.daysOfTheWeek = daysOfTheWeek;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public Date getInitialHour() {
        return initialHour;
    }

    public void setInitialHour(Date initialHour) {
        this.initialHour = initialHour;
    }

    public Date getLastHour() {
        return lastHour;
    }

    public void setLastHour(Date lastHour) {
        this.lastHour = lastHour;
    }

    public DaysOfTheWeek getDaysOfTheWeek() {
        return daysOfTheWeek;
    }

    public void setDaysOfTheWeek(DaysOfTheWeek daysOfTheWeek) {
        this.daysOfTheWeek = daysOfTheWeek;
    }
    
    public long durationHours() {
        long diff = lastHour.getTime() - initialHour.getTime();
        return TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public long durationMinutes() {
        long diff = lastHour.getTime() - initialHour.getTime();
        TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS);
        return (diff / (60 * 1000) % 60);
    }

    @Override
    public String toString() {
        return  daysOfTheWeek
                + ", " 
                + classes
                + ": "
                + sdf.format(initialHour) 
                + "-" 
                + sdf.format(lastHour) 
                + ". Total hours: "
                + durationHours()
                + "."
                + durationMinutes();
    }
}