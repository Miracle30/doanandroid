package com.example.model;

public class Notes {
    private int id;
    private String note="";
    private String content="";

    public Notes(int id, String note, String content) {
        this.id = id;
        this.note = note;
        this.content = content;
    }

    public Notes() {
    }
    public int getId() {
        return id;
    }

    public String getNote() {
        return (note);
    }

    public String getContent() {
        return (content);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString(){
        return(getNote());//tra ve ten title
    }
}
