package kr.ac.kopo.petapplication.model;
public class CareItem {

    public String type;
    public String title;
    public String date;
    public String detail;

    // 산책용
    public String time;
    public String location;

    // 예방접종용
    public String nextVaccine;

    public CareItem(String type, String title, String date, String detail) {
        this.type = type;
        this.title = title;
        this.date = date;
        this.detail = detail;
    }
}