package Converters;

import Converters.FormatWriter.FormatExcelWriter;
import Converters.HeadReaders.HeadExcelEnum;
import Converters.HeadReaders.HeadExcelReader;
import DTO.IncomingData;
import DTO.OutgoingData;
import DTO.OutgoingHead;
import Windows.MainWindow;
import com.microsoft.schemas.office.visio.x2012.main.impl.CellTypeImpl;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
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
            //System.out.println("Файл с названием in.xlsx не найден");
            MainWindow.getInstance().println("Файл с названием in.xlsx не найден");
            try {
                Thread.sleep(60000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
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
                //System.out.println("Строка " + (rowT.getRowNum()+1) + " c пустыми данными не была обработана" );
                MainWindow.getInstance().println("Строка " + (rowT.getRowNum()+1) + " c пустыми данными не была обработана");
            }
            catch (IllegalStateException e){
                MainWindow.getInstance().println("Строка " + (rowT.getRowNum()+1) + ": неверный формат данных " + e.getMessage());
            }

        }
        try {
            workbookIn.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (incomingStructure.size()==0){
           // System.out.println("Файл не содержит данных");
            MainWindow.getInstance().println("Файл не содержит данных");
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(1);
        }

        return incomingStructure;
    }

    public void writeData (OutgoingHead headInformation, List<OutgoingData> outgoingStructure, File fileName) {
        FormatExcelWriter  formatExcelWriter = new FormatExcelWriter();
        Workbook workbook = formatExcelWriter.write(headInformation,outgoingStructure);

        File excelFile = fileName;
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(excelFile);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            MainWindow.getInstance().println("\n\rСоздан файл out.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
