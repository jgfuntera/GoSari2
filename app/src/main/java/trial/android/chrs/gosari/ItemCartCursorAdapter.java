package trial.android.chrs.gosari;

import android.content.Context;
import android.database.Cursor;
import android.text.Layout;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by greg on 4/26/16.
 */

public class ItemCartCursorAdapter extends ArrayAdapter<ItemCart> {

    Context context;

    ArrayList<ItemCart>items;

    public ItemCartCursorAdapter (Context context,ArrayList<ItemCart> items) {
       super(context, R.layout.row_cart_items,items);
        this.context=context;
        this.items=items;

    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.row_cart_items,parent,false);
        TextView id=(TextView)view.findViewById(R.id.tvCart_Id);
        TextView item_pk=(TextView)view.findViewById(R.id.tvPk_Cart_Items);
        TextView categories_pk=(TextView)view.findViewById(R.id.tvPk_Cart_Items_Categories);
        TextView cart_item=(TextView)view.findViewById(R.id.tvCart_Items);
        TextView cart_description=(TextView)view.findViewById(R.id.tvCart_Description_Items);
        TextView cart_price=(TextView)view.findViewById(R.id.tvCart_Price_Items);
        TextView cart_count=(TextView)view.findViewById(R.id.tvCart_Count);
        TextView cart_deliverytime=(TextView)view.findViewById(R.id.tvCart_DeliveryTime);

        id.setText("ID: "+String.valueOf(items.get(position).id));
        item_pk.setText("PK: "+items.get(position).Cartpk);
        categories_pk.setText("Categories_PK: " +items.get(position).Cartpk_Categories);
        cart_item.setText("ITEMS: "+items.get(position).CartItems);
        cart_description.setText("DESCRIPTION: " +items.get(position).CartDescription);
        cart_price.setText("PRICE: " +String.valueOf(items.get(position).CartPrice));
        cart_deliverytime.setText("DELIVERY TIME: " +items.get(position).CartDeliveryTime);
        cart_count.setText("COUNT: "+String.valueOf(items.get(position).CartCount));

        return view;

    }


}

