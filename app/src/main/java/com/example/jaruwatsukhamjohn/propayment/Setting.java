package com.example.jaruwatsukhamjohn.propayment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Locale;


public class Setting extends ActionBarActivity {
    private Button LangButton;
    private Switch languageswitch;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        languageswitch = (Switch) findViewById(R.id.languageSwitch);
        SharedPreferences sharedPrefs = getSharedPreferences("com.example.jaruwatsukhamjohn.propayment", MODE_PRIVATE);
        languageswitch.setChecked(sharedPrefs.getBoolean("NameOfThingToSave", false));
        bindwidget();
        bindlistener();

        languageswitch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if((languageswitch.isChecked()))
                {
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.jaruwatsukhamjohn.propayment", MODE_PRIVATE).edit();
                    editor.putBoolean("NameOfThingToSave", true);
                    editor.commit();
                    Configuration config = new Configuration();
                    Locale locale = new Locale("th");
                    Locale.setDefault(locale);
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                    Toast.makeText(getApplicationContext(),"ไทย",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),MenuActivity.class));
                    finish();
                }
                else
                {
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.jaruwatsukhamjohn.propayment", MODE_PRIVATE).edit();
                    editor.putBoolean("NameOfThingToSave", false);
                    editor.commit();
                    Configuration config = new Configuration();
                    Locale locale = new Locale("");
                    Locale.setDefault(locale);
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                    Toast.makeText(getApplicationContext(),"English",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),MenuActivity.class));
                    finish();
                }
            }
        });


    }

    private void bindlistener() {
       /* LangButton.setOnClickListener(new View.OnClickListener() {

            boolean checklang = true;
            @Override
            public void onClick(View v) {
                Configuration config = new Configuration();
                Locale locale = new Locale("th");
                Locale.setDefault(locale);
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                Toast.makeText(getApplicationContext(),"Thai",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),MenuActivity.class));
                finish();
            }
        });*/


    }

    private void bindwidget() {
        title=getApplicationContext().getResources().getString(R.string.setting);
        this.setTitle(title);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
