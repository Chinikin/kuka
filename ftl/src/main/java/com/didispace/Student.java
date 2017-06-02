package com.didispace;

public class Student {  
	  
    private Integer id;  
    private String name;  
    private String city;  
    private Integer age;  
    private String sex;  
    //个人主页  
    private String url;  
      
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
    public String getCity() {  
        return city;  
    }  
    public void setCity(String city) {  
        this.city = city;  
    }  
    public Integer getAge() {  
        return age;  
    }  
    public void setAge(Integer age) {  
        this.age = age;  
    }  
    public String getSex() {  
        return sex;  
    }  
    public void setSex(String sex) {  
        this.sex = sex;  
    }  
      
      
      
      
    public String getUrl() {  
        return url;  
    }  
    public void setUrl(String url) {  
        this.url = url;  
    }  
    public Student(){}  
      
      
    public Student(Integer id, String name, String city, Integer age,  
            String sex, String url) {  
        super();  
        this.id = id;  
        this.name = name;  
        this.city = city;  
        this.age = age;  
        this.sex = sex;  
        this.url = url;  
    }  
    @Override  
    public String toString() {  
        return "Student [id=" + id + ", name=" + name + ", city=" + city  
                + ", age=" + age + ", sex=" + sex + ", url=" + url + "]";  
    }  
      
      
      
}  