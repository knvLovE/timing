package Converters.HeadReaders;

import java.util.Calendar;
import java.util.GregorianCalendar;

public enum HeadExcelEnum {
    REQUESTERNAME("Инициатор"),
    PROCESSINGNAME("Исполнитель"),
    WORKMINUTES("#time, мин"),
    SOLUTIONDATE("Дата/время  завершения работ");

    private String field;

    private HeadExcelEnum(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
