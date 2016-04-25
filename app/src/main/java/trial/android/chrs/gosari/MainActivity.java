package trial.android.chrs.gosari;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    private String UrlFood="http://192.168.1.11/shop/FUNCTIONS/categories/list.php";

    ImageView ivViewFood,ivViewNonFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivViewFood=(ImageView) findViewById(R.id.ivViewFood);

        ivViewFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ShowFoodCategory();

            }
        });

        ivViewNonFood=(ImageView)findViewById(R.id.ivViewNonFood);

        ivViewNonFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ShowNonFoodCategory();
            }
        });

    }

    public void ShowFoodCategory(){

        Intent showFoodCategory = new Intent(getApplicationContext(),ViewFood.class);
        startActivity(showFoodCategory);

    }

    public void ShowNonFoodCategory(){
        Intent showNonFoodCategory=new Intent(getApplicationContext(),ViewNonFood.class);
        startActivity(showNonFoodCategory);

    }
}
