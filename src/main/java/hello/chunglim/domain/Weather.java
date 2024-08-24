package hello.chunglim.domain;

public class Weather {
    private String serviceKey;
    private String baseDate;
    private String baseTime;
    private String dataType;
    private int nx;
    private int ny;

    public Weather(String baseDate, String baseTime) {
        this.baseDate = baseDate;
        this.baseTime = baseTime;
    }
}
