package Converters.HeadReaders;

import Windows.MainWindow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class HeadExcelReader {

    public Map<HeadExcelEnum, Integer> getColumnsNumber (Row head){
        Map<String, Integer> allHeadColumns = new HashMap<>();
        for (Cell cell : head){
            allHeadColumns.put(cell.getStringCellValue(),cell.getColumnIndex());
        }
        Map<HeadExcelEnum, Integer> KeyHeadColumns = new HashMap<>();
        HeadExcelEnum foundedField = null;
        Integer successRead = null;
        try {
            foundedField = HeadExcelEnum.REQUESTERNAME;
            KeyHeadColumns.put(foundedField, successRead = allHeadColumns.get(foundedField.getField()));
            if (successRead == null) throw new NullPointerException();
            foundedField = HeadExcelEnum.PROCESSINGNAME;
            KeyHeadColumns.put(foundedField, successRead = allHeadColumns.get(foundedField.getField()));
            if (successRead == null) throw new NullPointerException();
            foundedField = HeadExcelEnum.WORKMINUTES;
            KeyHeadColumns.put(foundedField, successRead = allHeadColumns.get(foundedField.getField()));
            if (successRead == null) throw new NullPointerException();
            foundedField = HeadExcelEnum.SOLUTIONDATE;
            KeyHeadColumns.put(foundedField, successRead = allHeadColumns.get(foundedField.getField()));
            if (successRead == null) throw new NullPointerException();
        } catch (Exception e){
           // System.out.println("нет колонки с названием: " + foundedField.getField());
           // System.out.println("Нажмите Enter для окончания");
            MainWindow.getInstance().println("нет колонки с названием: " + foundedField.getField());
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                reader.readLine();
            } catch (IOException ex) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch (IOException ex) {
                    e.printStackTrace();
                }
            }

            System.exit(1);
        }
        return KeyHeadColumns;
    }
}
