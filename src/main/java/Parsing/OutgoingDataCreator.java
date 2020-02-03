package Parsing;

import DTO.IncomingData;
import DTO.OutgoingData;
import Parsing.IntermediateStructure.WorkTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OutgoingDataCreator {
    public List<OutgoingData> parsing (List<IncomingData> incomingStructure){
        TimesParser timesParser = new TimesParser();
        List<WorkTime> workTimes = timesParser.parsing(incomingStructure);
        DatesParser datesParser = new DatesParser();
        int NormalMinutes = datesParser.parsWorkDays(incomingStructure).size();
        DeviationCalculator deviationCalculator = new DeviationCalculator();
        List<OutgoingData> outgoingStructure= deviationCalculator.calculate(workTimes,NormalMinutes);
        Collections.sort(outgoingStructure);
        return outgoingStructure;


    }

}
