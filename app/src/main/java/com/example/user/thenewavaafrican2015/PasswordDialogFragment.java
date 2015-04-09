package com.example.user.thenewavaafrican2015;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by Aaron on 07/04/2015.
 */
public class PasswordDialogFragment
{
    public static void onCreateDialog(final Context context, final Activity activity, final String username, final SharedPreferences sp)
    {
        UserDbHelper mDbHelper = new UserDbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        final Cursor c = db.rawQuery("SELECT " + UserContract.UserEntry.COLUMN_NAME_PASS + " FROM " + UserContract.UserEntry.TABLE_NAME + " WHERE " + UserContract.UserEntry.COLUMN_NAME_NAME + " =?", new String[] {username});
        c.moveToFirst();

        // Use the Builder class for convenient dialog construction
        final EditText input = new EditText(context);
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Password");
        input.setHint("Password");
        input.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);

        //Creating a simple linear layout for text input
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        builder.setView(input);
        //Sets a positive button and listens for user click
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            //simple function to check password input and compare. I know it's unsafe, but for a local app with no sensitive data it's alright.
            public void onClick(DialogInterface dialog, int id) {
                if ((input.getText().toString()).equals(c.getString(0)))
                {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("CurUsr", username);
                    editor.apply();
                    Log.d("Accounts", username + " successfully logged in");

                    //Show prompt sayings login success and creates new logic success dialog
                    AlertDialog.Builder loginSuccess = new AlertDialog.Builder(activity);
                    loginSuccess.setTitle("Login Success!");
                    loginSuccess.setMessage(username + " successfully logged in!");
                    loginSuccess.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id)
                        {
                            activity.finish();
                            activity.startActivity(activity.getIntent());
                        }
                    });
                    loginSuccess.create();
                    loginSuccess.show();
                }

            //If incorrect password
            else
            {
                //show prompt saying login fail
                Log.d("Accounts", username + " failed to log in");
                final AlertDialog.Builder loginFail = new AlertDialog.Builder(activity);
                loginFail.setTitle("Login Failed");
                loginFail.setMessage("Incorrect password for " + username);
                loginFail.setNegativeButton("Cancel", null);

                //Positive button to recall the original dialog should the user wish to retry the password.
                loginFail.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        PasswordDialogFragment.onCreateDialog(context, activity, username, sp);
                    }
                });
                loginFail.create();
                loginFail.show();
            }
        }
    })
        .setTitle("Enter Password for " + username)
        .setNegativeButton("Cancel", null);
        // Create the AlertDialog object and return it
        builder.create();
        builder.show();
    }
}
