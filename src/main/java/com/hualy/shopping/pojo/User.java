package com.hualy.shopping.pojo;

//将表和字段映射给实体类和属性
public class User {

    //id,name,addr,age
    //映射id字段
    private Integer id;

    //映射name字段
    private String name;

    //映射addr字段
    private String addr;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //映射age字段
    private Integer age;

    private String username;
    private String password;
    //getters and setters

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

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    //toString()
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", addr=" + addr + ", age=" + age + "]";
    }
}
