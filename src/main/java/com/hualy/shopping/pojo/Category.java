package com.hualy.shopping.pojo;

public class Category {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //id,name,addr,age
    //映射id字段
    private Integer id;

    //映射name字段
    private String name;

}
