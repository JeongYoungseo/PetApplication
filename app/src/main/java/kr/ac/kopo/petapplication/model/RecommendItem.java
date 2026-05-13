package kr.ac.kopo.petapplication.model;

public class RecommendItem {

    private String name;
    private String desc;
//    private int imageRes;
    private String category;
    private String price;


    public RecommendItem(String name, String desc, String category, String price){
        this.name = name;
        this.desc = desc;
//        this.imageRes = imageRes;
        this.category = category;
        this.price = price;
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
