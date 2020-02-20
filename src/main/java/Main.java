import Converters.Converter;
import Converters.FileXLSXConverter;
import Converters.Types.FileFormat;
import DTO.IncomingData;
import DTO.OutgoingData;
import DTO.OutgoingHead;
import Parsing.HeadInformationCreator;
import Parsing.OutgoingDataCreator;
import Windows.MainWindow;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // настраиваемые параметры типа входного и выходного файла
        FileFormat incomingFileFormat = FileFormat.XLSX;
        FileFormat outgoingFileFormat = FileFormat.XLSX;

        // настраиваемые параметры имени входного и выходного файла
        File incomingFileName = new File("in.xlsx") ;
        File outgoingFileName = new File("out.xlsx") ;

        Converter converter = null;
        if (incomingFileFormat == FileFormat.XLSX) {
             converter = new FileXLSXConverter();
        }

        //создание основной выходной структуры
        List<IncomingData> incomingDataList = converter.readData(incomingFileName);
        OutgoingDataCreator parser = new OutgoingDataCreator();
        List<OutgoingData> outgoingDataList = parser.parsing(incomingDataList);

        //создание дополнительной информации в шапке
        HeadInformationCreator headInformationCreator = new HeadInformationCreator();
        OutgoingHead outgoingHead = headInformationCreator.parsing(incomingDataList);

        if (outgoingFileFormat == FileFormat.XLSX) {
            converter = new FileXLSXConverter();
        }
        converter.writeData(outgoingHead, outgoingDataList, outgoingFileName);

        MainWindow.getInstance().println("Закройте окно перед поторным запуском");



    }
}
