package DTO;

public class OutgoingData implements Comparable <OutgoingData> {
    private String processingUserName; // имя обработавшего обращение
    private int totalRequestMinutes; //  в ремя на закритие заявок в минутах
    private int totalProjectMinutes; // время на проектные работы в минутах
    private int totalMinutes; // общее время
    private int normalWorkDays; // нормативное время в минутах
    private int normalWorkMinutes; // нормативное время в минутах
    private int deviationNormalMinutes; // отклонение от нормы в минутах
    private double deviationNormalHours; // отклонение от нормы в часах

    public OutgoingData(String processingUserName, int totalRequestMinutes, int totalProjectMinutes, int totalMinutes,
                        int normalWorkDays, int normalWorkMinutes, int deviationNormalMinutes, double deviationNormalHours) {
        this.processingUserName = processingUserName;
        this.totalRequestMinutes = totalRequestMinutes;
        this.totalProjectMinutes = totalProjectMinutes;
        this.totalMinutes = totalMinutes;
        this.normalWorkDays = normalWorkDays;
        this.normalWorkMinutes = normalWorkMinutes;
        this.deviationNormalMinutes = deviationNormalMinutes;
        this.deviationNormalHours = deviationNormalHours;

    }

    public String getProcessingUserName() {
        return processingUserName;
    }

    public int getTotalRequestMinutes() {
        return totalRequestMinutes;
    }

    public int getNormalWorkDays() {
        return normalWorkDays;
    }

    public int getNormalWorkMinutes() {
        return normalWorkMinutes;
    }

    public int getTotalProjectMinutes() {
        return totalProjectMinutes;
    }

    public int getTotalMinutes() {
        return totalMinutes;
    }

    public int getDeviationNormalMinutes() {
        return deviationNormalMinutes;
    }

    public double getDeviationNormalHours() {
        return deviationNormalHours;
    }


    @Override
    public int compareTo(OutgoingData o) {

        return o.getDeviationNormalMinutes() - this.getDeviationNormalMinutes() ;
    }
}
