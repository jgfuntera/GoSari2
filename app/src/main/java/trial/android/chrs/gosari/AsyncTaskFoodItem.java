package trial.android.chrs.gosari;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by greg on 4/25/16.
 */
public class AsyncTaskFoodItem extends AsyncTask<String, String, JSONObject> {


    public static String url=null;

    @Override
    protected JSONObject doInBackground(String... args) {

       JSONParser jParser=new JSONParser();
        JSONObject json=jParser.getJSONFromUrl(url);
        return json;
    }
    @Override
    protected void onPostExecute(JSONObject json) {

        ViewFoodItems.adapter.notifyDataSetChanged();


            try{
                ViewFoodItems.message=json.getString("msg");
                ViewFoodItems.result=json.getJSONArray("result");
                for(int i=0;i<ViewFoodItems.result.length();i++) {
                    JSONObject r=ViewFoodItems.result.getJSONObject(i);
                    FoodItems foodItems = new FoodItems();
                    foodItems.setPk(r.getString("pk"));
                    foodItems.setPk_categories(r.getString("categories_pk"));
                    foodItems.setItems(r.getString("item"));
                    foodItems.setPrice(r.getString("price"));
                    foodItems.setDescription(r.getString("description"));
                    foodItems.setCount(r.getString("count"));
                    foodItems.setArchived(r.getString("archived"));
                    ViewFoodItems.ItemfoodList.add(foodItems);
                }

            }
            catch (JSONException e){
                e.printStackTrace();
            }

        }

    }

