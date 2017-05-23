package com.example.adrian.git;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Clasa pentru a afisa imagini in loc de text intr-un Spinner
 */
public class ImageArrayAdapter extends ArrayAdapter<Integer> {

    public ImageArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull Integer[] objects) {
        super(context, resource, objects);
    }

    /**
     * Override ca sa returneze un ImageView in loc de TextView
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent)
    {
        ImageView imagine = new ImageView(getContext());
        try{
            imagine.setImageResource(getItem(position));
        }
        catch(NullPointerException e)
        {
            Toast.makeText(getContext(), "NullPointerException", Toast.LENGTH_LONG).show();
            imagine.setImageResource(android.R.drawable.ic_delete);
        }
        return imagine;
    }

    /**
     * Override ca sa returneze un ImageView in loc de TextView
     */
    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent)
    {
        ImageView imagine = new ImageView(getContext());
        try{
            imagine.setImageResource(getItem(position));
        }
        catch(NullPointerException e)
        {
            Toast.makeText(getContext(), "NullPointerException", Toast.LENGTH_LONG).show();
            imagine.setImageResource(android.R.drawable.ic_delete);
        }
        return imagine;
    }
}
