package Parsing.IntermediateStructure;

public class WorkTime {
    private String processingUserName; // имя обработавшего обращение
    private int totalRequestMinutes; //  время на закритие заявок в минутах
    private int totalProjectMinutes; // время на проектные работы в минутах

    public WorkTime(String processingUserName, int totalRequestMinutes, int totalProjectMinutes) {
        this.processingUserName = processingUserName;
        this.totalRequestMinutes = totalRequestMinutes;
        this.totalProjectMinutes = totalProjectMinutes;
    }

    public String getProcessingUserName() {
        return processingUserName;
    }

    public int getTotalRequestMinutes() {
        return totalRequestMinutes;
    }

    public int getTotalProjectMinutes() {
        return totalProjectMinutes;
    }
}
