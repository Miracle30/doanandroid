package com.example.doanfinal;

public class Notes {
    private static String note="";

    public static String getContent() {
        return content;
    }

    public static void setContent(String content) {
        Notes.content = content;
    }

    private static String content="";

    public static String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public Notes(){ }
    public String toString(){
    return(getNote());//tra ve ten title
}
}
