package com.example.doanfinal;

public class Notes {
    private String title="";
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Notes(){ }
    public String toString(){
    return(getTitle());//tra ve ten title
}
}
