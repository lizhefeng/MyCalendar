package com.example.zhefengli.mycalendar;

/**
 * Created by zhefengli on 3/7/16.
 */
public class Reminder {
    String title;
    String memo;
    String date;

    public Reminder(){

    }

    public Reminder(String title, String memo, String date){
        this.title = title;
        this.memo = memo;
        this.date = date;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public void setMemo(String memo){
        this.memo = memo;
    }

    public String getMemo(){
        return this.memo;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDate(){
        return this.date;
    }

    public boolean equals(Object object)
    {
        boolean same = false;

        if (object != null && object instanceof Reminder)
        {
            same = (this.title.equals(((Reminder) object).title)) && (this.date.equals(((Reminder) object).date)) && (this.memo.equals(((Reminder) object).memo));
        }

        return same;
    }
}
