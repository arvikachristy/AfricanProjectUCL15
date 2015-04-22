package com.example.user.thenewavaafrican2015;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.PriorityQueue;

/**
 * Created by Aaron on 20/03/2015.
 */
public class Medication implements Parcelable
{
    //attributes
    private String medName;
    private int dosage;
    private int courseLength;
    private String startDate;
    private String endDate;
    private String nextDose;
    private String prevDose;

    //Writes Medication data to Parcel
    public void writeToParcel(Parcel p, int x)
    {
        p.writeString(medName);
        p.writeInt(dosage);
        p.writeInt(courseLength);
        p.writeString(startDate);
        p.writeString(endDate);
        p.writeString(nextDose);
        p.writeString(prevDose);
    }

    //Creates Medication from Parcel
    public Medication createFromParcel(Parcel source)
    {
        Medication m = new Medication();
        m.medName = source.readString();
        m.dosage = source.readInt();
        m.courseLength = source.readInt();
        m.startDate = source.readString();
        m.endDate = source.readString();
        m.nextDose = source.readString();
        m.prevDose = source.readString();
        return m;
    }

    public int describeContents(){
        return 0;
    }

    public Medication(String nmedName, int ndosage, int ncourseLength)
    {
        this.medName = nmedName;
        this.dosage = ndosage;
        this.courseLength = ncourseLength;
        startDate();
        endDate();
        nextDose();
    }

    public Medication()
    {

    }

    //gets current start date
    public void startDate()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        this.startDate = dateFormat.format(date);
    }

    //calc end date
    public void endDate()
    {

    }

    //gets next date and sets previous date
    public void nextDose()
    {

    }

    public String getName() {
        return medName;
    }
    public int getCourseLength(){return courseLength;}
    public String getStartDate(){return startDate;}
    public int getDosage(){return dosage;}
}
