package Parsing;

import DTO.IncomingData;
import DTO.OutgoingHead;
import Parsing.IntermediateStructure.WorkTime;
import org.apache.poi.hssf.record.SaveRecalcRecord;

import java.text.SimpleDateFormat;
import java.util.*;

public class HeadInformationCreator {
    public OutgoingHead parsing (List<IncomingData> incomingStructure) {
        DatesParser datesParser = new DatesParser();
        List<Calendar> datesList = new ArrayList<>(datesParser.parsAllDays(incomingStructure));
        Collections.sort(datesList);
        SimpleDateFormat dateFormat =  new SimpleDateFormat ("dd.MM.yyyy");
        String startDate = dateFormat.format(datesList.get(0).getTime());
        String endDate = dateFormat.format(datesList.get(datesList.size()-1).getTime());
        List <String> head = new ArrayList<>();
        head.add("Период " + startDate + " - " + endDate);
        head.add("Норму часов нужно увеличить на время работы в выходной день");
        head.add("Норму часов нужно уменьшить на время отуствий из табеля рабочего времени");

        return  new OutgoingHead(head);
    }
}
