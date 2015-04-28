package com.example.user.thenewavaafrican2015;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MyDosage extends Activity {
    EditText medNameTxt, freqTxt, minTxt;
    TimePicker timeTxt;
    List<Medication> Medicines = new ArrayList<Medication>();
    ListView medicineListView;

    private ScheduleClient scheduleClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("debug", "Started class");
        try
        {
            populateList();
        }
        catch(Exception e)
        {
            Log.e("Error","No Entries found in Medication List");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dosage);

        //Schedule declaration
        /*
        scheduleClient = new ScheduleClient(this);
        scheduleClient.doBindService();
        */

        //Text declarations

        medNameTxt = (EditText)findViewById(R.id.editMed);
        timeTxt = (TimePicker)findViewById(R.id.timepick);
        //This should be passed in
        freqTxt = (EditText)findViewById(R.id.editRemFreq);
        minTxt = (EditText)findViewById(R.id.editRemMin);
        medicineListView = (ListView)findViewById(R.id.medicineListView);

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("add");
        tabSpec.setContent(R.id.add);
        tabSpec.setIndicator("Add Medicine");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("store");
        tabSpec.setContent(R.id.medlist);
        tabSpec.setIndicator("Your Medicines");
        tabHost.addTab(tabSpec);

        //Cast some of the things




        final Button addBtn =(Button) findViewById(R.id.button);
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)  {

                final String medName = medNameTxt.getText().toString();
                final String medDosage = freqTxt.getText().toString();
                final String medTiming = minTxt.getText().toString();
                addMedicine(medName, Integer.parseInt(medDosage), Integer.parseInt(medTiming));
                populateList();
                Toast.makeText(getApplicationContext(), medName + " has been added to your medicines", Toast.LENGTH_SHORT).show();

                //Create a new calendar

                Calendar c = Calendar.getInstance();

                //Need to do some work on setting

                //scheduleClient.setAlarmForNotification(c);

            }
        });



        minTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addBtn.setEnabled(FieldsFull());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }

    private boolean FieldsFull()
    {
        if (medNameTxt.getText().toString().equals(""))
        {
            return false;
        }
        if(freqTxt.getText().toString().equals(""))
        {
            return false;
        }
        if(minTxt.getText().toString().equals(""))
        {
            return false;
        }
        return true;
    }


    private void populateList()
    {
        ArrayAdapter<Medication> adapter = new MedicineListAdapter();
        medicineListView.setAdapter(adapter);
    }


    private void addMedicine(String medNameTxt, int dosage, int timeBetween)
    {
        /*
        Medication m = new Medication(medNameTxt, dosage, timeBetween);
        Globals.curUsr.addToList(m);
        MedicineDBAccessor dbAccessor = new MedicineDBAccessor(getApplicationContext());
        dbAccessor.insertNewMedication(m);
        */

        Medicines.add(new Medication(medNameTxt, dosage, timeBetween));
    }

    private class MedicineListAdapter extends ArrayAdapter<Medication>
    {
        public MedicineListAdapter() {
            super (MyDosage.this, R.layout.listview_item, Medicines);
        }


        @Override
        public View getView(int position, View view, ViewGroup parent)
        {
            if (view == null)
                view = getLayoutInflater().inflate(R.layout.listview_item, parent, false);

            //Medication currentMedicine = Globals.curUsr.getFromList(position);
            Medication currentMedicine = Medicines.get(position);
            TextView name = (TextView) view.findViewById(R.id.medicineName);
            name.setText(currentMedicine.getName());

            TextView time = (TextView) view.findViewById(R.id.medicineName);
            time.setText(currentMedicine.getName());

            TextView frequency = (TextView) view.findViewById(R.id.doseTime);
            frequency.setText(currentMedicine.getName());

            TextView distribution = (TextView) view.findViewById(R.id.doseDistri);
            distribution.setText(currentMedicine.getName());

            return view;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_dosage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }
}
