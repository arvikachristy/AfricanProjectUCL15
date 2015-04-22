package com.example.user.thenewavaafrican2015;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MyMedicine extends ActionBarActivity {
    EditText medNameTxt, timeTxt, freqTxt, minTxt;
    List<Medication> Medicines = new ArrayList<Medication>();
    ListView medicineListView;

    private ScheduleClient scheduleClient;

    //Create a new service client








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_medicine);

        //Schedule declaration
        scheduleClient = new ScheduleClient(this);
        scheduleClient.doBindService();

        //Text declarations

        medNameTxt = (EditText) findViewById(R.id.editMed);
        timeTxt = (EditText) findViewById(R.id.editRemTime);
        //This should be passed in
        freqTxt = (EditText) findViewById(R.id.editRemFreq);
        minTxt = (EditText) findViewById(R.id.editRemMin);
        medicineListView = (ListView) findViewById(R.id.listView);

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

        final String medName = medNameTxt.toString();
        final String medDosage = freqTxt.toString();
        final String medTiming = minTxt.toString();

        final int nDosage = Integer.parseInt(medDosage);
        final int nTiming = Integer.parseInt(medTiming);

        final Button addBtn =(Button) findViewById(R.id.button);
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)  {
                addMedicine(medName, nDosage, nTiming);
                populateList();
                Toast.makeText(getApplicationContext(), medNameTxt.getText().toString() + " has been added to your medicines", Toast.LENGTH_SHORT).show();

                //Create a new calendar

                Calendar c = Calendar.getInstance();

                //Need to do some work on setting

                scheduleClient.setAlarmForNotification(c);

            }
        });



        medNameTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addBtn.setEnabled(!medNameTxt.getText().toString().trim().isEmpty());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }


    private void populateList()
    {
        ArrayAdapter<Medication> adapter = new MedicineListAdapter();
        medicineListView.setAdapter(adapter);
    }


    private void addMedicine(String medNameTxt, int dosage, int timeBetween)
    {
        Medicines.add(new Medication(medNameTxt, dosage, timeBetween));
    }

    private class MedicineListAdapter extends ArrayAdapter<Medication>
    {
        public MedicineListAdapter() {
            super (MyMedicine.this, R.layout.listview_item, Medicines);
        }


        @Override
        public View getView(int position, View view, ViewGroup parent)
        {
            if (view == null)
                view = getLayoutInflater().inflate(R.layout.listview_item, parent, false);

            Medication currentMedicine = Medicines.get(position);

            TextView name = (TextView) view.findViewById(R.id.medicineName);
            name.setText(currentMedicine.getName());

            TextView time = (TextView) view.findViewById(R.id.medicineName);
            name.setText(currentMedicine.getName());

            TextView frequency = (TextView) view.findViewById(R.id.doseTime);
            time.setText(currentMedicine.getName());

            TextView distribution = (TextView) view.findViewById(R.id.doseDistri);
            distribution.setText(currentMedicine.getName());

            return view;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_medicine, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

}
