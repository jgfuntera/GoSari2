package trial.android.chrs.gosari;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.widget.TextView;

/**
 * Created by greg on 5/1/16.
 */
public class ItemChkoutHistoryAdapter extends ArrayAdapter<ItemCheckout>{
    ArrayList<ItemCheckout>itemCheckouts;
    Context context;
    ViewHolder holder;

    public ItemChkoutHistoryAdapter (Context context,ArrayList<ItemCheckout> itemCheckouts) {
        super(context,R.layout.row_chkout_history,itemCheckouts);
        this.itemCheckouts = itemCheckouts;
        this.context=context;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {

        holder=new ViewHolder();
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.row_chkout_history,parent,false);

        holder.chkout_pk=(TextView)view.findViewById(R.id.tvPk_ChkOut_Items);
        holder.chkout_categpk=(TextView)view.findViewById(R.id.tvPk__ChkOut_Items_Categories);
        holder.chkout_item=(TextView)view.findViewById(R.id.tv_ChkOut_Items);
        holder.chkout_price=(TextView)view.findViewById(R.id.tv_ChkOut_Price_Items);
        holder.chkout_count=(TextView)view.findViewById(R.id.tv_ChkOut_Count);
        holder.chkout_date=(TextView)view.findViewById(R.id.tv_ChkOut_Date);


        holder.chkout_pk.setText("PK: "+itemCheckouts.get(position).chkout_itempk);
        holder.chkout_categpk.setText("PK_CATEGORIES: "+itemCheckouts.get(position).chkout_categpk);
        holder.chkout_item.setText("ITEM: "+itemCheckouts.get(position).chkout_item);
        holder.chkout_price.setText("PRICE: "+String.valueOf(itemCheckouts.get(position).chkout_price));
        holder.chkout_count.setText("COUNT: "+String.valueOf(itemCheckouts.get(position).chkout_count));
        holder.chkout_date.setText("DATE: "+itemCheckouts.get(position).chkout_time);

        return view;

    }

    class ViewHolder{
        TextView chkout_pk;
        TextView chkout_categpk;
        TextView chkout_item;
        TextView chkout_price;
        TextView chkout_count;
        TextView chkout_date;

    }
}
