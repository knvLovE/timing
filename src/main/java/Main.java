import Converters.Converter;
import Converters.FileXLSXConverter;
import Converters.Types.Format;
import DTO.IncomingData;
import DTO.OutgoingData;
import DTO.OutgoingHead;
import Parsing.HeadInformationCreator;
import Parsing.OutgoingDataCreator;
import Windows.MainWindow;
import Windows.Window;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // настраиваемые параметры типа входного и выходного файла
        Format incomingFormat = Format.XLSX;
        Format outgoingFormat = Format.XLSX;

        // настраиваемые параметры имени входного и выходного файла
        File incomingFileName = new File("in.xlsx") ;
        File outgoingFileName = new File("out.xlsx") ;

        Converter converter = null;
        if (incomingFormat == Format.XLSX) {
             converter = new FileXLSXConverter();
        }

        //создание основной выходной структуры
        List<IncomingData> incomingDataList = converter.readData(incomingFileName);
        OutgoingDataCreator parser = new OutgoingDataCreator();
        List<OutgoingData> outgoingDataList = parser.parsing(incomingDataList);

        //создание дополнительной информации в шапке
        HeadInformationCreator headInformationCreator = new HeadInformationCreator();
        OutgoingHead outgoingHead = headInformationCreator.parsing(incomingDataList);

        if (outgoingFormat == Format.XLSX) {
            converter = new FileXLSXConverter();
        }
        converter.writeData(outgoingHead, outgoingDataList, outgoingFileName);


       // System.out.println("Нажмите Enter для окончания");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
