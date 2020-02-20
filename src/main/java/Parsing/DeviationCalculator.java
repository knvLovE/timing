package Parsing;

import DTO.OutgoingData;
import Parsing.IntermediateStructure.WorkTime;

import java.util.ArrayList;
import java.util.List;

public class DeviationCalculator {
    public List<OutgoingData> calculate (List<WorkTime> workTime, int normalDays){
        // создание выходной структуры с отклонениями от нормы часов
        List<OutgoingData> outgoingDataList = new ArrayList<>();
        for (WorkTime workTimeElement : workTime){
            String processingUserName = workTimeElement.getProcessingUserName();
            int totalRequestMinutes = workTimeElement.getTotalRequestMinutes();
            int totalProjectMinutes = workTimeElement.getTotalProjectMinutes();
            int totalMinutes = totalRequestMinutes + totalProjectMinutes;
            int normalWorkDays = normalDays;
            int normalWorkMinutes = normalDays*8*60;
            int deviationNormalMinutes =  totalMinutes - normalWorkMinutes;
            double deviationNormalHours = deviationNormalMinutes * 10 / 60 / 10.0;
            OutgoingData outgoingData = new OutgoingData(processingUserName, totalRequestMinutes, totalProjectMinutes,
                    totalMinutes, normalWorkDays, normalWorkMinutes, deviationNormalMinutes, deviationNormalHours);
            outgoingDataList.add(outgoingData);
        }

        return outgoingDataList;
    }
}
