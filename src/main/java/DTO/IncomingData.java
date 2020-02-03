package DTO;

import java.lang.reflect.GenericArrayType;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class IncomingData {
    private String requesterUserName; //  имя обратившегося
    private String processingUserName; // имя обработавшего обращение
    private int workMinutes ; // работа в минутах одной заявки
    private Calendar solutionDate = new GregorianCalendar(); // дата решения

    public IncomingData(String requesterUserName, String processingUserName, double workMinutes, Date solutionDate) {
        this.requesterUserName = requesterUserName;
        this.processingUserName = processingUserName;
        this.workMinutes = (int)workMinutes;
        this.solutionDate.setTime(solutionDate);
    }



    public String getRequesterUserName() {
        return requesterUserName;
    }

    public String getProcessingUserName() {
        return processingUserName;
    }

    public int getWorkMinutes() {
        return workMinutes;
    }

    public Calendar getSolutionDate() {
        return solutionDate;
    }
}
