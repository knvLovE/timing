package Parsing;

import DTO.IncomingData;
import DTO.OutgoingData;
import Parsing.IntermediateStructure.WorkTime;

import java.util.*;

public class TimesParser {
    public List<WorkTime> parsing (List<IncomingData> incomingStructure){

        Map<String, WorkTime> pivotTable = new HashMap<>();

        for (IncomingData line : incomingStructure){
            int requestMinutes=0;
            int projectMinutes=0;
            if (line.getRequesterUserName().equals(line.getProcessingUserName())){
                projectMinutes = line.getWorkMinutes();
            } else {
                requestMinutes = line.getWorkMinutes();
            }
            String keyName = line.getProcessingUserName();

            if (pivotTable.containsKey(keyName) ){
                requestMinutes += pivotTable.get(keyName).getTotalRequestMinutes();
                projectMinutes += pivotTable.get(keyName).getTotalProjectMinutes();
                WorkTime workTimeUpdate = new WorkTime(keyName,requestMinutes,projectMinutes);
                pivotTable.replace(keyName,workTimeUpdate);
            } else {
                WorkTime workTime = new WorkTime(keyName,requestMinutes,projectMinutes);
                pivotTable.put(keyName, workTime);
            }

        }
        List<WorkTime> resultList = new ArrayList<>(pivotTable.values());

        return resultList;
    }

}
