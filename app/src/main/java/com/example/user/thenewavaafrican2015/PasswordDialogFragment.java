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
    public static void onCreateDialog(Context context, final Activity activity, final String username, final SharedPreferences sp)
    {
        UserDbHelper mDbHelper = new UserDbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        final Cursor c = db.rawQuery("SELECT " + UserContract.UserEntry.COLUMN_NAME_PASS + " FROM " + UserContract.UserEntry.TABLE_NAME + " WHERE " + UserContract.UserEntry.COLUMN_NAME_NAME + " =?", new String[] {username});
        c.moveToFirst();
        // Use the Builder class for convenient dialog construction
        final EditText input = new EditText(context);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Password: ")
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if((input.getText().toString()).equals(c.getString(0)))
                        {
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("CurUsr", username);
                            editor.apply();
                            Log.d("Accounts", username + " successfully logged in");
                            activity.finish();
                            activity.startActivity(activity.getIntent());
                            //Show prompt sayings login success
                        }
                        else
                        {
                            //show prompt saying login fail
                            Log.d("Accounts", username + " failed to log in");
                        }
                    }
                })
                .setTitle("Password Entry")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        input.setHint("Password");
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        builder.setView(input);
        // Create the AlertDialog object and return it
        builder.create();
        builder.show();
    }
}
