package kr.ac.kopo.petapplication.model;

public class RecommendItem {

    private String name;
    private String desc;
//    private int imageRes;
    private String category;
    private String price;
    public int score;



    public RecommendItem(String name, String desc, String category, String price, int score){
        this.name = name;
        this.desc = desc;
//        this.imageRes = imageRes;
        this.category = category;
        this.price = price;
        this.score = score;
    }

    public String getName(){
        return name;
    }
    public String getDesc(){
        return desc;
    }

//    public int getImageRes(){
//        return imageRes;
//    }
    public String getCategory(){
        return category;
    }
    public String getPrice(){
        return price;
    }
}
