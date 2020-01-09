package com.example.administrator.shopping.bean;

public class ShopBean {
    private String id;
    private String ShoppingTitle;
    private String shoppingPrice;
    private  int img;

    public void setImg(int img) {
        this.img = img;
    }

    public int getImg() {
        return img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShoppingTitle() {
        return ShoppingTitle;
    }

    public void setShoppingTitle(String shoppingTitle) {
        ShoppingTitle = shoppingTitle;
    }

    public String getShoppingPrice() {
        return shoppingPrice;
    }

    public void setShoppingPrice(String shoppingPrice) {
        this.shoppingPrice = shoppingPrice;
    }
}