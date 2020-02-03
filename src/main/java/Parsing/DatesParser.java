package Parsing;

import DTO.IncomingData;
import DTO.OutgoingData;
import org.apache.poi.ss.formula.functions.Days360;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

public class DatesParser {


    private Set<Calendar> parsing (List<IncomingData> incomingStructure){
        Set<Calendar> dates = new HashSet<>();
        for (IncomingData line : incomingStructure){
            Calendar date = line.getSolutionDate();
            date.set(Calendar.AM_PM,0);
            date.set(Calendar.HOUR,0);
            date.set(Calendar.MINUTE,0);
            date.set(Calendar.SECOND,0);
            dates.add(line.getSolutionDate());
        }
        return dates;
    }

    public Set<Calendar> parsAllDays (List<IncomingData> incomingStructure){
        return parsing (incomingStructure);
    }

    public Set<Calendar> parsWorkDays (List<IncomingData> incomingStructure){
        Set<Calendar> dates = parsing (incomingStructure);
        Set<Calendar> datesUpdate = new HashSet<>();
        for (Calendar date : dates){
            int datOfWeek = date.get(Calendar.DAY_OF_WEEK);
            if (!(datOfWeek == Calendar.SATURDAY || datOfWeek == Calendar.SUNDAY)){
                datesUpdate.add(date);
            }
        }
        return datesUpdate;
    }




}
