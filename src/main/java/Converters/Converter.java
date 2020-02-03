package Converters;

import DTO.IncomingData;
import DTO.OutgoingData;
import DTO.OutgoingHead;

import java.io.File;
import java.util.List;

public interface Converter {

    List<IncomingData> readData (File fileName);

    void writeData (OutgoingHead headInformation, List<OutgoingData> structure, File fileName);

}
