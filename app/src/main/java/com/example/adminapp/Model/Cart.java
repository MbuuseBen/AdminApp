package com.example.adminapp.Model;

public class Cart {
    private String pid, pname, image, discount;
private int quantity,price;
    public Cart() {
    }

    public Cart(String pid, String pname, String image, String discount, int quantity, int price) {
        this.pid = pid;
        this.pname = pname;
        this.image = image;
        this.discount = discount;
        this.quantity = quantity;
        this.price = price;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}