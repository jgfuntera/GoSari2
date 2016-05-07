package trial.android.chrs.gosari;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class  AcitivityMain extends AppCompatActivity {

    Button btnStart;

    DbHelper db;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain);

        db=new DbHelper(this);

        btnStart=(Button)findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                boolean accountCheck=db.checkfirst();

                if(accountCheck==false) {
                    ShowCategory();
                }
                else{
                    ShowRegisterAccount();
                }
            }
        });

    }


    public void ShowCategory(){
            Intent viewCategory=new Intent(getApplicationContext(),ViewCategory_new.class);
            startActivity(viewCategory);

    }


    public void ShowRegisterAccount(){
        Intent showRegisterAccount=new Intent(getApplicationContext(),RegisterAccount.class);
        startActivity(showRegisterAccount);
    }
}