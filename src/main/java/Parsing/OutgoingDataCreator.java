package Parsing;

import DTO.IncomingData;
import DTO.OutgoingData;
import Parsing.IntermediateStructure.WorkTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OutgoingDataCreator {
    public List<OutgoingData> parsing (List<IncomingData> incomingStructure){
        //метод создает готовую выходную структуру.

        //создание часть структуры по времени работы в разрезе каждого исполнителя
        TimesParser timesParser = new TimesParser();
        List<WorkTime> workTimes = timesParser.parsing(incomingStructure);

        //создание часть структуры подсчета количества рабочих дней
        DatesParser datesParser = new DatesParser();
        int normalDays = datesParser.parsWorkDays(incomingStructure).size();

        // создание общей выходной структуры с отклонениями от нормы часов
        DeviationCalculator deviationCalculator = new DeviationCalculator();
        List<OutgoingData> outgoingStructure= deviationCalculator.calculate(workTimes,normalDays);
        Collections.sort(outgoingStructure);

        return outgoingStructure;


    }

}
