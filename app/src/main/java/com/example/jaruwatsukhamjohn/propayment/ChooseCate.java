package com.example.jaruwatsukhamjohn.propayment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class ChooseCate extends ActionBarActivity {


    private ImageView award, basketball, book, box, cake, coffee, debt, diamond,
            dice, drink, education, entertainment, family, food, football, give, icecream, invest
            ,loan,music,phone,picture,salary,selling,shopping,transport,travel,tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_cate);
        this.setTitle("Choose Category");
        bindwidget();
        bindlistener();

    }

    private void bindwidget() {
        award = (ImageView) findViewById(R.id.imgcate_award);
        basketball = (ImageView) findViewById(R.id.imgcate_basketball);
        book = (ImageView) findViewById(R.id.imgcate_book);
        box = (ImageView) findViewById(R.id.imgcate_box);
        cake = (ImageView) findViewById(R.id.imgcate_cake);
        coffee = (ImageView) findViewById(R.id.imgcate_coffee);
        debt = (ImageView) findViewById(R.id.imgcate_debt);
        diamond = (ImageView) findViewById(R.id.imgcate_diamond);
        dice = (ImageView) findViewById(R.id.imgcate_dice);
        drink = (ImageView) findViewById(R.id.imgcate_drink);
        education = (ImageView) findViewById(R.id.imgcate_education);
        entertainment = (ImageView) findViewById(R.id.imgcate_entertainment);
        family = (ImageView) findViewById(R.id.imgcate_family);
        food = (ImageView) findViewById(R.id.imgcate_food);
        football = (ImageView) findViewById(R.id.imgcate_football);
        give = (ImageView) findViewById(R.id.imgcate_give);
        icecream = (ImageView) findViewById(R.id.imgcate_icecream);
        invest = (ImageView) findViewById(R.id.imgcate_invest);
        loan = (ImageView) findViewById(R.id.imgcate_loan);
        music = (ImageView) findViewById(R.id.imgcate_music);
        //other = (ImageView) findViewById(R.id.imgcate_award);
        phone = (ImageView) findViewById(R.id.imgcate_phone);
        picture = (ImageView) findViewById(R.id.imgcate_picture);
        salary = (ImageView) findViewById(R.id.imgcate_salary);
        selling = (ImageView) findViewById(R.id.imgcate_selling);
        shopping = (ImageView) findViewById(R.id.imgcate_shopping);
        transport = (ImageView) findViewById(R.id.imgcate_transport);
        travel = (ImageView) findViewById(R.id.imgcate_travel);
        tv = (ImageView) findViewById(R.id.imgcate_tv);

    }

    private void bindlistener() {
        award.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendString("ic_award");
            }
        });
        basketball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendString("ic_basketball");
            }
        });
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendString("ic_book");
            }
        });
        box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendString("ic_box");
            }
        });
        cake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendString("ic_cake");
            }
        });
        coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendString("ic_coffee");
            }
        });
        debt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendString("ic_debt");
            }
        });
        diamond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendString("ic_diamond");
            }
        });
        drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendString("ic_drink");
            }
        });
        dice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendString("ic_dice");
            }
        });
        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendString("ic_education");
            }
        });
        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {sendString("ic_entertainment");    }
        });
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendString("ic_family");
            }
        });
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendString("ic_food");
            }
        });
        football.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendString("ic_football");
            }
        });
        give.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendString("ic_give");
            }
        });
        icecream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendString("ic_icecream");
            }
        });
        invest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendString("ic_invest");
            }
        });
        loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendString("ic_loan");
            }
        });
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendString("ic_music");
            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendString("ic_phone");
            }
        });
        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendString("ic_picture");
            }
        });
        salary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendString("ic_salary");
            }
        });
        selling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendString("ic_selling");
            }
        });
        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendString("ic_shopping");
            }
        });
        transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendString("ic_transport");
            }
        });
        travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendString("ic_travel");
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendString("ic_tv");
            }
        });
    }


    public void sendString(String sicon) {
        Intent intent = new Intent();
        intent.putExtra("category", sicon);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choose_cate, menu);
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
