package trial.android.chrs.gosari;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by greg on 4/25/16.
 */
public class ItemFoodAdapter extends ArrayAdapter<FoodItems> {
    ArrayList<FoodItems>FoodList;
    LayoutInflater vi;
    int Resource;
    ViewHolder holder;



    public ItemFoodAdapter(Context context, int resource, ArrayList<FoodItems> objects) {

        super(context,resource,objects);
        vi=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource=resource;
        FoodList=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v=convertView;

        if(v==null){

            holder=new ViewHolder();
            v=vi.inflate(Resource,null);
            holder.tvpk=(TextView)v.findViewById(R.id.tvPk_Items);
            holder.tvpk_categories=(TextView)v.findViewById(R.id.tvPk_Items_Categories);
            holder.tvitems=(TextView)v.findViewById(R.id.tvItem);
            holder.tvprice=(TextView)v.findViewById(R.id.tvPrice_Items);
            holder.tvdescription=(TextView)v.findViewById(R.id.tvDescription_Items);
        }
        else {

            holder=new ViewHolder();
        }
        holder.tvpk.setText("PK: "+FoodList.get(position).getPk());
        holder.tvpk_categories.setText("PK_CATEGORIES: "+FoodList.get(position).getPk_categories());
        holder.tvitems.setText("ITEMS: "+FoodList.get(position).getItems());
        holder.tvprice.setText("PRICE: "+FoodList.get(position).getPrice());
        holder.tvdescription.setText("DESCRIPTION: "+FoodList.get(position).getDescription());


        return v;
    }

    static class ViewHolder{
        public TextView tvpk;
        public TextView tvpk_categories;
        public TextView tvitems;
        public TextView tvprice;
        public TextView tvdescription;

    }
}
