package Converters;

import Converters.HeadReaders.HeadExcelEnum;
import Converters.HeadReaders.HeadExcelReader;
import DTO.IncomingData;
import DTO.OutgoingData;
import DTO.OutgoingHead;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class FileXLSXConverter implements Converter {

    public List<IncomingData> readData(File fileName)  {

        File excelFile = fileName;

        FileInputStream fileInputStream = null;
        XSSFWorkbook workbookIn = null;
        try {
            fileInputStream = new FileInputStream(excelFile);
            workbookIn = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            System.out.println("Файл с названием in.xlsx не найден");
            System.exit(1);

        }

        List <IncomingData> incomingStructure = new ArrayList<>();

        XSSFSheet sheetIn = workbookIn.getSheetAt(0);

        HeadExcelReader headExcelReader = new HeadExcelReader();
        Map <HeadExcelEnum,Integer> keyHeadExcel = null;
        for (Row rowT : sheetIn){


            try {
                if (rowT.getRowNum()==0) {

                    keyHeadExcel = headExcelReader.getColumnsNumber(rowT);
                    continue;
                };
                String requesterUserName = rowT.getCell(keyHeadExcel.get(HeadExcelEnum.REQUESTERNAME)).getStringCellValue();
                String processingUserName = rowT.getCell(keyHeadExcel.get(HeadExcelEnum.PROCESSINGNAME)).getStringCellValue();
                double workMinutes = rowT.getCell(keyHeadExcel.get(HeadExcelEnum.WORKMINUTES)).getNumericCellValue();
                Date solutionDate = rowT.getCell(keyHeadExcel.get(HeadExcelEnum.SOLUTIONDATE)).getDateCellValue();
                IncomingData incomingData = new IncomingData(requesterUserName, processingUserName, workMinutes, solutionDate);
                incomingStructure.add(incomingData);
            } catch (NullPointerException e){
                System.out.println("Строка " + (rowT.getRowNum()+1) + " c пустыми данными не была обработана" );
            }

        }
        try {
            workbookIn.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (incomingStructure.size()==0){
            System.out.println("Файл не содержит данных");
            System.exit(1);
        }



        return incomingStructure;
    }

    public void writeData (OutgoingHead headInformation, List<OutgoingData> outgoingStructure, File fileName) {
        Workbook workbookOut = new XSSFWorkbook();
        Sheet sheet = workbookOut.createSheet("Result");

        int currentLine = headInformation.getHead().size() + 1;


        String [] headTable = {"ФИО","Заявки мин.","Проект мин.","Общее мин.","Норма дни","Норма мин.",
                "Разница мин.","Разница час."};
        Row row = sheet.createRow(currentLine);
        for (int i = 0 ; i< headTable.length; i++ ){
            Cell cell = row.createCell(i);
            cell.setCellValue(headTable[i]);
        }
        currentLine ++;

        for (OutgoingData line: outgoingStructure){
            row = sheet.createRow(currentLine);

            for (int i = 0 ; i< headTable.length; i++ ){

            }
            Cell cell0 = row.createCell(0);
            cell0.setCellValue(line.getProcessingUserName());
            Cell cell1 = row.createCell(1);
            cell1.setCellValue(line.getTotalRequestMinutes()+"");
            Cell cell2 = row.createCell(2);
            cell2.setCellValue(line.getTotalProjectMinutes()+"");
            Cell cell3 = row.createCell(3);
            cell3.setCellValue(line.getTotalMinutes()+"");
            Cell cell4 = row.createCell(4);
            cell4.setCellValue(line.getNormalWorkDays()+"");
            Cell cell5 = row.createCell(5);
            cell5.setCellValue(line.getNormalWorkMinutes()+"");
            Cell cell6 = row.createCell(6);
            cell6.setCellValue(line.getDeviationNormalMinutes()+"");
            Cell cell7 = row.createCell(7);
            cell7.setCellValue(line.getDeviationNormalHours()+"");
            currentLine ++;
        }

        for (int i = 0; i < headTable.length; i++) {
            sheet.autoSizeColumn(i);
        }

        currentLine = 0;

        for (String line : headInformation.getHead()){
            row = sheet.createRow(currentLine);
            Cell cell = row.createCell(0);
            cell.setCellValue(line);
            currentLine++;
        }

        File excelFile = fileName;
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(excelFile);
            workbookOut.write(fileOut);
            fileOut.close();
            workbookOut.close();
            System.out.println("Создан файл out.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
