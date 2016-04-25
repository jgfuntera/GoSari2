package trial.android.chrs.gosari;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by greg on 4/23/16.
 */
public class ItemAdapter extends ArrayAdapter<ItemCategory>{
        ArrayList<ItemCategory>ItemList;
        LayoutInflater vi;
        int Resource;
        ViewHolder holder;


    public ItemAdapter(Context context, int resource, ArrayList<ItemCategory> objects) {
        super(context, resource, objects);
        vi=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        ItemList=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v=convertView;

        if(v==null){
            holder=new ViewHolder();
            v=vi.inflate(Resource,null);
            holder.tvpk=(TextView)v.findViewById(R.id.tvPk);
            holder.tvCategory=(TextView)v.findViewById(R.id.tvCategory);
            //holder.tvDescription=(TextView)v.findViewById(R.id.tvDescription);
            v.setTag(holder);

        }
        else {
            holder=(ViewHolder)v.getTag();
        }
        holder.tvpk.setText("PK: "+ItemList.get(position).getPk());
        holder.tvCategory.setText(ItemList.get(position).getCategory());
        //holder.tvDescription.setText(ItemList.get(position).getDescription());

        return v;
    }

    static class ViewHolder{

        public TextView tvpk;
        public TextView tvCategory;
      //  public TextView tvDescription;

    }


}

