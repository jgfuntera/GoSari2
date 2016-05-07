package trial.android.chrs.gosari;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.app.Activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class ViewCart extends AppCompatActivity {

    ItemCartCursorAdapter customAdapter;

    private DbHelper helper;

    ArrayList<ItemCart> itemCarts;

    ArrayList<ItemCart> newData;

    ArrayList<ItemCheckout> itemCheckouts;

    ArrayList<ItemCheckout> newDataChkOut;


    Button chkout;

    TextView tvTotalPrice, tvNoofItems;

    int totalprice, totalitems, itemprice, itemcount, updateprice = 0, newprice = 0;

    int count = 1, getcount;

    String item;

    DbHelper db;

    ListView listView;

    int mYear, mMonth, mDay, mHour, mMinute;

    String date = null, time = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_items);

        db = new DbHelper(this);

        tvTotalPrice = (TextView) findViewById(R.id.tvTotalPrice);
        tvNoofItems = (TextView) findViewById(R.id.tvTotalItems);

        helper = new DbHelper(this);

        totalprice = helper.getTotalPrice();

        totalitems = helper.getItemCount();

        tvTotalPrice.setText("TOTAL PRICE: " + String.valueOf(totalprice));

        tvNoofItems.setText("#ITEMS: " + String.valueOf(totalitems));

        chkout = (Button) findViewById(R.id.btnCheckout);

        listView = (ListView) findViewById(R.id.list_cart);


        itemCarts = helper.getAllItem();

        newData = new ArrayList<ItemCart>();

        for (ItemCart items : itemCarts) {

            newData.add(items);

        }

        customAdapter = new ItemCartCursorAdapter(this, newData);

        listView.setAdapter(customAdapter);


        chkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialogChkOut();
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                item = itemCarts.get(position).getCartItems();
                itemprice = itemCarts.get(position).getCartPrice();
                itemcount = itemCarts.get(position).getCartCount();


                showDialog();

            }
        });


    }

    public void showDialog() {

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewCart.this);


        alertDialogBuilder.setTitle("Cart Itmes");

        alertDialogBuilder.setMessage("Item: " + item + "\n" + "Count: " + itemcount + "\n" + "Price: " + itemprice);


        alertDialogBuilder.setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "UPDATE!!", Toast.LENGTH_SHORT).show();

                showUpdateDialog();

            }
        });

        alertDialogBuilder.setNegativeButton("REMOVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                Log.e("DELETING", "DELETED");
                db.deletItem(item);
                recreate();

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

    public void showUpdateDialog() {

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewCart.this);

        LayoutInflater layoutInflater = LayoutInflater.from(ViewCart.this);
        View v = View.inflate(this, R.layout.edit_text_2, null);

        alertDialogBuilder.setView(v);

        final EditText etCart = (EditText) v.findViewById(R.id.etCart2);
        final Button btnAdd = (Button) v.findViewById(R.id.btnAdd2);
        final Button btnSub = (Button) v.findViewById(R.id.btnSub2);

        etCart.setText(String.valueOf(itemcount));


        alertDialogBuilder.setTitle("ENTER NEW NUMBER OF COUNTS?");
        alertDialogBuilder.setMessage("Item: " + item);

        InputMethodManager mgr = (InputMethodManager) this.getSystemService(Service.INPUT_METHOD_SERVICE);
        mgr.showSoftInput(etCart, 0);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strcount = etCart.getText().toString();
                getcount = Integer.parseInt(strcount);
                getcount++;
                if (getcount > 999) {
                    getcount = 1;
                }
                etCart.setText(String.valueOf(getcount));

            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strcount = etCart.getText().toString();
                getcount = Integer.parseInt(strcount);
                getcount--;
                if (getcount <= 0) {
                    getcount = 1;
                }
                etCart.setText(String.valueOf(getcount));
            }
        });

        alertDialogBuilder.setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
                String strcount = etCart.getText().toString();
                getcount = Integer.parseInt(strcount);
                updateprice = itemprice / itemcount;
                newprice = getcount * updateprice;

                Log.e("UPDATE: ", "ITEM HAS BEEN UPDATED");
                db.updateItem(item, newprice, getcount);
                recreate();

            }
        });

        alertDialogBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

    public void ShowDialogChkOut() {

        AlertDialog.Builder alertdialogbuiler = new AlertDialog.Builder(ViewCart.this);
        alertdialogbuiler.setTitle("DO YOU WANT TO CHECKOUT");
        alertdialogbuiler.setMessage("# OF ITEMS: " + String.valueOf(totalitems) + "\n" +
                "TOTAL PRICE: " + String.valueOf(totalprice));

        alertdialogbuiler.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.e("INSERT: ", "ADDING TO CHKOUT TABLE");

                db.InserToChkout();

                db.removeAllItem();

                Log.e("DELETING: ", "ALL ITEMS HAS BEEN DELETED");

                itemCheckouts = db.getAllItemChkout();

                newDataChkOut = new ArrayList<ItemCheckout>();

                for (ItemCheckout itemCheckout : itemCheckouts) {
                    String message = "ID: " + itemCheckout.getChkout_id() + "ITEM: " + itemCheckout.chkout_item +
                            "DATE: " + itemCheckout.chkout_time + "PRICE: " + itemCheckout.chkout_price;
                    Log.e("DATA CHKOUT: ", message);
                }
                recreate();
                Toast.makeText(getApplicationContext(), "Successfully chekcout items", Toast.LENGTH_SHORT).show();
            }
        });

        alertdialogbuiler.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });


        alertdialogbuiler.setNeutralButton("Set Checkout Time", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                ShowDateTimePickerDialog();

            }
        });

        AlertDialog alertDialoag = alertdialogbuiler.create();
        alertDialoag.show();

    }

    public void ShowDateTimePickerDialog() {
        final AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = LayoutInflater.from(ViewCart.this);
        View v = View.inflate(this, R.layout.date_time_picker, null);
        alertdialogbuilder.setView(v);


        final EditText indate = (EditText) v.findViewById(R.id.in_date);
        final EditText intime = (EditText) v.findViewById(R.id.in_time);
        final Button btnsetdate = (Button) v.findViewById(R.id.btn_date);
        final Button btnsettime = (Button) v.findViewById(R.id.btn_time);

        alertdialogbuilder.setTitle("Set date and time to deliver items");
        alertdialogbuilder.setMessage("Item: " + item + "\n" + "Count: " + itemcount + "\n" + "Price: " + itemprice);

        btnsetdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                Log.e("MESSAGE:","ERROR STARTS HERE-->testdialog();");
                testdialog();





            }
        });

        btnsettime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                c.set(2014, 0, 1);

                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);
                Log.e("MESSAGE: ", "LAUNCHING DATE TIME");
                showtimepicker();
                intime.setText(time);


            }
        });

        AlertDialog alertDialog = alertdialogbuilder.create();
        alertDialog.show();


    }

    public void showdatepicker() {

        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {


                        // Display Selected date in textbox
                        date = (dayOfMonth + "-"
                                + (monthOfYear + 1) + "-" + year);
                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }

    public void showtimepicker() {

        TimePickerDialog tpd = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        // Display Selected time in textbox
                        time = (hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        tpd.show();

    }

    public void testdialog(){

        final CustomDatePickerDialog pickerDialog = new CustomDatePickerDialog(this,
                myDateListener, mYear, mMonth, mDay);
        pickerDialog.setMaxDate(System.currentTimeMillis());
        pickerDialog.show();
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {

        }
    };


}






