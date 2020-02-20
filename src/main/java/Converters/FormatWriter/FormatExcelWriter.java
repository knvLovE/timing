package Converters.FormatWriter;

import DTO.OutgoingData;
import DTO.OutgoingHead;
import Windows.MainWindow;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class FormatExcelWriter {
    private Workbook workbookOut;
    private Sheet sheet;
    private Row row;
    private int currentLine;

    public Workbook write (OutgoingHead headInformation, List<OutgoingData> outgoingStructure){

        FormatExcelVariants format = FormatExcelVariants.FORMULAFORMAT;
//        FormatExcelVariants format = FormatExcelVariants.BASEFORMAT;

        workbookOut = new XSSFWorkbook();
        sheet = workbookOut.createSheet("Result");

        currentLine = headInformation.getHead().size() + 1;


        String [] headTable = {"ФИО","Заявки мин.","Проект мин.","Общее мин.","Норма дни","Норма мин.",
                "Разница мин.","Разница час."};
        row = sheet.createRow(currentLine);
        for (int i = 0 ; i< headTable.length; i++ ){
            Cell cell = row.createCell(i);
            cell.setCellValue(headTable[i]);
        }
        currentLine ++;

        for (OutgoingData line: outgoingStructure){
            row = sheet.createRow(currentLine);
            String formula = "";
            Cell cell0 = row.createCell(0);
            cell0.setCellValue(line.getProcessingUserName());
            Cell cell1 = row.createCell(1);
            cell1.setCellValue(line.getTotalRequestMinutes());
            Cell cell2 = row.createCell(2);
            cell2.setCellValue(line.getTotalProjectMinutes());
            Cell cell3 = row.createCell(3);
            cell3.setCellValue(line.getTotalMinutes());
            Cell cell4 = row.createCell(4);
            cell4.setCellValue(line.getNormalWorkDays());

            Cell cell5 = row.createCell(5);
            if (format == FormatExcelVariants.BASEFORMAT){
                cell5.setCellValue(line.getNormalWorkMinutes());
            }
            else if (format == FormatExcelVariants.FORMULAFORMAT){
                formula = String.format("E%d*8*60",currentLine+1);
                cell5.setCellFormula(formula);
            }

            Cell cell6 = row.createCell(6);
            if (format == FormatExcelVariants.BASEFORMAT){
                cell6.setCellValue(line.getDeviationNormalMinutes());
            }
            else if (format == FormatExcelVariants.FORMULAFORMAT){
                formula = String.format("D%d-F%d",currentLine+1,currentLine+1);
                cell6.setCellFormula(formula);
            }

            Cell cell7 = row.createCell(7);
            if (format == FormatExcelVariants.BASEFORMAT){
                cell7.setCellValue(line.getDeviationNormalHours());
            }
            else if (format == FormatExcelVariants.FORMULAFORMAT){
                formula = String.format("G%d/60",currentLine+1);
                DataFormat dataFormat = workbookOut.createDataFormat();
                CellStyle cellStyle = workbookOut.createCellStyle();
                cellStyle.setDataFormat(dataFormat.getFormat("0.0"));
                cell7.setCellStyle(cellStyle);
                cell7.setCellFormula(formula);
            }

            currentLine ++;
        }

        for (int i = 0; i < headTable.length; i++) {
            sheet.autoSizeColumn(i);
        }

        //добавление шапки после автовыравнивания по всей таблице основных данных
        currentLine = 0;

        for (String line : headInformation.getHead()){
            row = sheet.createRow(currentLine);
            Cell cell = row.createCell(0);
            cell.setCellValue(line);
            currentLine++;
        }
        return workbookOut;


    }

}
