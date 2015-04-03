package com.example.user.thenewavaafrican2015;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.PriorityQueue;

/**
 * Created by Aaron on 20/03/2015.
 */
public class Medication
{
    //attributes
    private String medName;
    private int dosage;
    private int courseLength;
    private String startDate;
    private String endDate;
    private String nextDose;
    private String prevDose;

    public Medication(String nmedName, int ndosage, int ncourseLength)
    {
        this.medName = nmedName;
        this.dosage = ndosage;
        this.courseLength = ncourseLength;
        startDate();
        endDate();
        nextDose();
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
}
