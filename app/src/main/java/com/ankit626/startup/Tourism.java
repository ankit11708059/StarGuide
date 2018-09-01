package com.ankit626.startup;

/**
 * Created by Ankit Sharma on 13-03-2018.
 */

public class Tourism {
    private String name,price,desc;
    public Tourism(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Tourism(String name, String price, String image, String desc){
      this.name=name;
      this.price=price;
      this.desc=desc;

  }
}
