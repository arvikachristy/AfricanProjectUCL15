package com.example.user.thenewavaafrican2015;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ImageView;
import android.database.sqlite.SQLiteDatabase;

public class MyProfileNew extends ActionBarActivity {
    ImageView callprofilepicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_profile_new);

        callprofilepicture = (ImageView) findViewById(R.id.profilePicture);

        callprofilepicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
            }
        });
    }

    public void onActivityResult(int reqCode, int resCode, Intent data){
        if (resCode == RESULT_OK) {
            if (reqCode == 1)
                callprofilepicture.setImageURI(data.getData());
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_profile_new, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        RelativeLayout main_view = (RelativeLayout) findViewById(R.id.main_view);
        LinearLayout mainL_view;

        while (true){
            switch(item.getItemId()){
                case R.id.vika_red:
                    if(item.isChecked())
                        item.setChecked(false);
                    else
                        item.setChecked(true);
                    main_view.setBackgroundResource(R.drawable.circus);

                    return true;

                case R.id.aaron_green:
                    if(item.isChecked())
                        item.setChecked(false);
                    else
                        item.setChecked(true);
                    main_view.setBackgroundResource(R.drawable.park);
                    return true;

                case R.id.aidan_blue:
                    if(item.isChecked())
                        item.setChecked(false);
                    else
                        item.setChecked(true);
                    main_view.setBackgroundResource(R.drawable.underwater);
                    return true;

                default:
                    return super.onOptionsItemSelected(item);
            }
        }
    }
    public User loadProfile(String name)
    {
        try
        {
            UserDbHelper mDbHelper = new UserDbHelper(getApplicationContext());
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM " + UserContract.UserEntry.TABLE_NAME + " WHERE " + UserContract.UserEntry.COLUMN_NAME_NAME + " = ?", new String[] {name});
            SharedPreferences settings = getSharedPreferences("UsrPrefs", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("CurUsr", name);
            editor.commit();
            User retVal = new User(c.getString(1), c.getInt(2), c.getInt(3));
            return retVal;
        }
        catch(Exception e)
        {
            return null;
        }
    }
}
