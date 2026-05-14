package kr.ac.kopo.petapplication.model;

public class RecommendItem {
    int imageRes;
    private String name;
    private String desc;
//    private int imageRes;
    private String category;
    private String price;

    public int score;




    public RecommendItem(int imageRes, String name, String desc, String category, String price,  int score){

        this.imageRes = imageRes;
        this.name = name;
        this.desc = desc;
        this.category = category;
        this.price = price;
        this.score = score;
    }
    public int getImageRes() { return imageRes; }

    public String getName(){
        return name;
    }
    public String getDesc(){
        return desc;
    }

    public String getCategory(){
        return category;
    }
    public String getPrice(){
        return price;
    }
}
