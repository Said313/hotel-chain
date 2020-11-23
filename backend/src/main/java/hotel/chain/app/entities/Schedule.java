package hotel.chain.app.entities;

import java.sql.Time;
import java.util.Date;

public class Schedule {
    private int userID;
    private Time mondayStart;
    private Time mondayEnd;
    private Time tuesdayStart;
    private Time tuesdayEnd;
    private Time wednesdayStart;
    private Time wednesdayEnd;
    private Time thursdayStart;
    private Time thursdayEnd;
    private Time fridayStart;
    private Time fridayEnd;
    private Time saturdayStart;
    private Time saturdayEnd;
    private Time sundayStart;
    private Time sundayEnd;
    private float payroll;
    
    public Schedule(int userID,  Time mondayStart, Time mondayEnd, Time tuesdayStart, Time tuesdayEnd,
                    Time wednesdayStart, Time wednesdayEnd, Time thursdayStart, Time thursdayEnd,
                    Time fridayStart, Time fridayEnd, Time saturdayStart, Time saturdayEnd,
                    Time sundayStart, Time sundayEnd, float payroll) {

        this.userID = userID;
        this.mondayStart = mondayStart;
        this.mondayEnd = mondayEnd;
        this.tuesdayStart = tuesdayStart;
        this.tuesdayEnd = tuesdayEnd;
        this.wednesdayStart = wednesdayStart;
        this.wednesdayEnd = wednesdayEnd;
        this.thursdayStart = thursdayStart;
        this.thursdayEnd = thursdayEnd;
        this.fridayStart = fridayStart;
        this.fridayEnd = fridayEnd;
        this.saturdayStart = saturdayStart;
        this.saturdayEnd = saturdayEnd;
        this.sundayStart = sundayStart;
        this.sundayEnd = sundayEnd;
        this.payroll = payroll;
    }

    public Schedule() {
        userID = 0;
        mondayStart = new Time(new Date().getTime());
        mondayEnd = new Time(new Date().getTime());
        tuesdayStart = new Time(new Date().getTime());
        tuesdayEnd = new Time(new Date().getTime());
        wednesdayStart = new Time(new Date().getTime());
        wednesdayEnd = new Time(new Date().getTime());
        thursdayStart = new Time(new Date().getTime());
        thursdayEnd = new Time(new Date().getTime());
        fridayStart = new Time(new Date().getTime());
        fridayEnd = new Time(new Date().getTime());
        saturdayStart = new Time(new Date().getTime());
        saturdayEnd = new Time(new Date().getTime());
        sundayStart = new Time(new Date().getTime());
        sundayEnd = new Time(new Date().getTime());
        payroll = 0;
    }
}
