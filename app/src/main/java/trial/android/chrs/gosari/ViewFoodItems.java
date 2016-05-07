package trial.android.chrs.gosari;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import android.content.ContentValues;

import android.content.Context;


public class ViewFoodItems extends AppCompatActivity {

    public static ArrayList<ItemFood> ItemfoodList;

    static ItemFoodAdapter adapter;

    public static JSONArray result = null;


    public static String message;

    int count = 1,totalprice=0,newcount=0;

    String getCount;

    Button btnViewCart;

    String pk;
    String pk_categories;
    String items;
    String description;
    String price;
    String delivery_time;

    DbHelper db;

    String dbmessage;

    String totalcount,s_totalprice;

    static ArrayList<ItemCart>itemlist;

    String date;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_items);

        db=new DbHelper(this);



        ItemfoodList = new ArrayList<ItemFood>();

        AsyncTaskFoodItem.test = true;

        new AsyncTaskFoodItem(ViewFoodItems.this).execute();

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

        ListView listitem = (ListView) findViewById(R.id.list_food);

        adapter = new ItemFoodAdapter(getApplicationContext(), R.layout.row_food_items, ItemfoodList);

        listitem.setAdapter(adapter);



        listitem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {


                pk = ItemfoodList.get(position).getPk();
                pk_categories = ItemfoodList.get(position).getPk_categories();
                items = ItemfoodList.get(position).getItems();
                description = ItemfoodList.get(position).getDescription();
                price = ItemfoodList.get(position).getPrice();
                delivery_time = ItemfoodList.get(position).getDeliverytime();

                ShowAlertDialog();


            }



        });

        btnViewCart=(Button)findViewById(R.id.btnCart2);

        btnViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ShowCartItem();

            }
        });


    }

        public void ShowAlertDialog() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        LayoutInflater layoutInflater = LayoutInflater.from(ViewFoodItems.this);

        View promptView = View.inflate(this, R.layout.edit_text, null);


        alertDialog.setView(promptView);

        alertDialog.setTitle(items);


        final EditText etCart = (EditText) promptView.findViewById(R.id.etCart);
        final Button btnAdd = (Button) promptView.findViewById(R.id.btnAdd);
        final Button btnSub = (Button) promptView.findViewById(R.id.btnSub);


        InputMethodManager mgr=(InputMethodManager)this.getSystemService(Service.INPUT_METHOD_SERVICE);

        mgr.showSoftInput(etCart,0);

        etCart.setText("1");


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;

                etCart.setText(String.valueOf(count));
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (count < 0) {
                    count--;
                } else {
                    count = 1;
                }
                etCart.setText(String.valueOf(count));

            }
        });


        alertDialog.setMessage("Description: " + description + "\n" + "Price: " + price);

        alertDialog.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                Toast.makeText(getApplicationContext(), "YOU CLICKED CANCEL", Toast.LENGTH_SHORT).show();

                dialogInterface.cancel();
            }
        });

        alertDialog.setNegativeButton("ADD TO CART", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {


                getCount=etCart.getText().toString();

                newcount=Integer.parseInt(getCount);

                boolean verify=db.verifyItem(items);

                if(verify==true){
                    Toast.makeText(getApplicationContext(),"ITEM IS ALREADY IN THE CART",Toast.LENGTH_SHORT).show();
                    count=1;

                }
                else {

                    InserData();
                    count=1;

                }

            }
        });

        alertDialog.show();

    }

        public void ShowCartItem(){

            Intent showCartItem=new Intent(getApplicationContext(),ViewCart.class);
            startActivity(showCartItem);
        }

        public void InserData(){

            DateFormat df=new SimpleDateFormat("EEE,MMM d, yyyy");

            date=df.format(Calendar.getInstance().getTime());

            totalprice=Integer.parseInt(price)*Integer.parseInt(getCount);

            Log.d("Inserting to Databse:","Cart Items");

            db.addItem(new ItemCart(pk,pk_categories,items,description,totalprice,newcount,delivery_time,date));

        }

        public void CartAlert(){
            AlertDialog.Builder alertdialogbuilder=new AlertDialog.Builder(this);
            alertdialogbuilder.setTitle("Item is already in the cart check your cart.");
            AlertDialog alertDialog=alertdialogbuilder.create();
            alertDialog.show();
        }

}
