package trial.android.chrs.gosari;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.text.AlteredCharSequence;
import android.text.Layout;
import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;


public class ViewFoodItems extends AppCompatActivity {

    public static ArrayList<FoodItems>ItemfoodList;

    static ItemFoodAdapter adapter;

    public static JSONArray result=null;


    public static String message;

    public String item=null;

    public String description=null;

    public String price=null;

    int test=1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_items);

        ItemfoodList=new ArrayList<FoodItems>();

        new AsyncTaskFoodItem().execute();

        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

        ListView listitem=(ListView)findViewById(R.id.list_food);

        adapter=new ItemFoodAdapter(getApplicationContext(),R.layout.row_food_items,ItemfoodList);

        listitem.setAdapter(adapter);



        listitem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                item=ItemfoodList.get(position).getItems();
                description=ItemfoodList.get(position).getDescription();
                price=ItemfoodList.get(position).getPrice();
                ShowAlertDialog();
            }
        });

    }

    public void ShowAlertDialog(){


        LayoutInflater layoutInflater=LayoutInflater.from(ViewFoodItems.this);
        View promptView=layoutInflater.inflate(R.layout.edit_text,null);

        final EditText etCart=(EditText)promptView.findViewById(R.id.etText);
        final Button btnAdd=(Button)promptView.findViewById(R.id.btnAdd);
        final Button btnSub=(Button)promptView.findViewById(R.id.btnSub);

        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);

        alertDialog.setView(promptView);
        alertDialog.setTitle("CONFIRM PURCHASE");
        alertDialog.setMessage("Item: "+item+"\n"+"Description: "+description+"\n"+"Price: "+price+"\n"+"Delivery Time: 30mins");



        etCart.setText(String.valueOf(test));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test++;

                etCart.setText(String.valueOf(test));
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test--;
                etCart.setText(String.valueOf(test));

            }
        });

        alertDialog.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                Toast.makeText(getApplicationContext(),"YOU CLICKED CANCEL",Toast.LENGTH_SHORT).show();

                dialogInterface.cancel();
            }
        });

        alertDialog.setNegativeButton("ADD TO CART", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                Toast.makeText(getApplicationContext(),"YOU CLICKED ADD TO CART",Toast.LENGTH_SHORT).show();


            }
        });

        AlertDialog alert=alertDialog.create();
        alert.show();
    }


}
