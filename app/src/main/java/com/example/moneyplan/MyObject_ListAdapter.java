package  com.example.moneyplan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.ArrayList;
import java.util.List;

public class MyObject_ListAdapter extends ArrayAdapter
{
    List list = new ArrayList();

    public MyObject_ListAdapter(@NonNull Context context, int resource)
    {
        super(context, resource);
    }

    static class LayoutHandler
    {
        TextView tanggal, namaCatatan, jumlah;

    }

    @Override
    public void add(@Nullable Object object)
    {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        LayoutHandler layoutHandler;

        if(row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.rowdata, parent, false);

            layoutHandler = new LayoutHandler();
            layoutHandler.tanggal = (TextView) row.findViewById(R.id.text_tanggal);
            layoutHandler.namaCatatan = (TextView) row.findViewById(R.id.text_nama_catatan);
            layoutHandler.jumlah = (TextView) row.findViewById(R.id.text_jumlah);

            row.setTag(layoutHandler);
        }
        else
        {
            layoutHandler = (LayoutHandler) row.getTag();
        }

        MyObject object = (MyObject)this.getItem(position);

        layoutHandler.tanggal.setText(object.getDate());
        layoutHandler.namaCatatan.setText(object.getNamaCatatan());
        layoutHandler.jumlah.setText(object.getJumlah());


        return row;
    }
}