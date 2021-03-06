package trial.android.chrs.gosari;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;

public class AsyncTaskItem extends AsyncTask<String, String, JSONObject> {

    public static int UrlChoice;


    String url;

    private ProgressDialog dialog;

    public AsyncTaskItem(ViewFood activity) {
        dialog = new ProgressDialog(activity);
    }
    @Override
    protected void onPreExecute() {
        dialog.setTitle("GoSari");
        dialog.setMessage("Loading items, please wait...");
        dialog.setCancelable(false);
        dialog.show();
    }


    @Override
    protected JSONObject doInBackground(String... args) {
        JSONParser jParser = new JSONParser();

        switch (UrlChoice) {
            case 1:
                url = "http://192.168.1.11/shop/FUNCTIONS/categories/list.php";
                break;
            case 2:
                url = "http://192.168.1.16/shop/FUNCTIONS/categories/list.php";
                break;

        }

        JSONObject json = jParser.getJSONFromUrl(url);
        return json;
    }

    @Override
    protected void onPostExecute(JSONObject json) {

     if(json==null){
         dialog.setMessage("NO CONNECTION");

     }
        else {


         dialog.dismiss();
         ViewFood.adapter.notifyDataSetChanged();
     }



        try {
            ViewFood.message = json.getString("msg");
            ViewFood.catergories = json.getJSONArray("result");
            for (int i = 0; i < ViewFood.catergories.length(); i++) {
                JSONObject c = ViewFood.catergories.getJSONObject(i);
                ItemCategory foods = new ItemCategory();
                foods.setPk(c.getString("pk"));
                foods.setCategory(c.getString("category"));
                //foods.setDescription(c.getString("description"));
                foods.setArchived(c.getString("archived"));
                ViewFood.foodList.add(foods);

            }



        } catch (JSONException e) {
            e.printStackTrace();

        }catch (NullPointerException e){
            e.printStackTrace();
            Log.e("MESSAGE","NO CONNECTION-->");

        }


    }


}