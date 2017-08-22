package com.example.jaruwatsukhamjohn.propayment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import DB.myDBclass;


public class AddCategory extends ActionBarActivity {

    ImageView image;
    String simage, dataicon, iconname;
    private Button add,cancel;
    ChooseCate choose = new ChooseCate();
    private Spinner spinner;
    String[] type;
    myDBclass mydb;
    private EditText catename;
    boolean changepic = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        this.setTitle("Add Category");
        bindwidget();
        bindlistener();


    }


    private void bindlistener() {

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = catename.getText().toString();
                if (changepic) {
                    Toast.makeText(getApplicationContext(), "Please Select Category", Toast.LENGTH_SHORT).show();
                }

                if (name.equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), "Please Enter Category Name", Toast.LENGTH_SHORT).show();

                } else if (dataicon.equalsIgnoreCase("income")) {
                    mydb.INSERTINCATE(null, name, "false", iconname);
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    mydb.INSERTEXCATE(null, name, "false", iconname);
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }


        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChooseCate.class);
                startActivityForResult(intent, 1);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dataicon = type[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void bindwidget() {

        mydb = new myDBclass(this);
        mydb.getWritableDatabase();

        catename = (EditText) findViewById(R.id.Catename);
        //image
        image = (ImageView) findViewById(R.id.img_addcategory);

        cancel = (Button) findViewById(R.id.cancel_category_button);
        add = (Button) findViewById(R.id.add_category_button);
        spinner = (Spinner) findViewById(R.id.spinnertype);
        type = getResources().getStringArray(R.array.type);

        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, type);
        spinner.setAdapter(adapterType);
        
        String setspinner = getIntent().getExtras().getString("type");
        if(setspinner.equalsIgnoreCase("in")){
            spinner.setSelection(0);
        }else {
            spinner.setSelection(1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                simage = data.getStringExtra("category");
                String tmp[] = simage.split("[_]");
                iconname = tmp[1];
                Resources res = getResources();
                String mDrawableName = simage;
                int resID = res.getIdentifier(mDrawableName, "drawable", getPackageName());
                Drawable drawable = res.getDrawable(resID);
                image.setImageDrawable(drawable);
                changepic = false;
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_category, menu);
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
