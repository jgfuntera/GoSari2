package trial.android.chrs.gosari;

/**
 * Created by greg on 4/24/16.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;



public class ViewNonFood extends AppCompatActivity {

    public static String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nonfood);

        AsyncTaskItem.UrlChoice=2;

        //new AsyncTaskItem().execute();

        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();



    }

}

