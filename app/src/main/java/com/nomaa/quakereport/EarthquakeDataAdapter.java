package com.nomaa.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import android.graphics.drawable.GradientDrawable;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by nomaa on 6/13/2017.
 */

public class EarthquakeDataAdapter extends ArrayAdapter <EarthquakeData> {

    private String primaryLocation;
    private String locationOffset;

    private String magnitude;

    private static final String LOCATION_SEPERATOR = " of ";

    public EarthquakeDataAdapter(Activity context, ArrayList<EarthquakeData> data){
        super(context, 0, data);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_data_layout,
                    parent, false);
        }

        EarthquakeData data = getItem(position);

        String originalLocation = data.getLocation();

        if(originalLocation.contains(LOCATION_SEPERATOR)){
            String[] parts = originalLocation.split(LOCATION_SEPERATOR);
            locationOffset = parts[0] + LOCATION_SEPERATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        magnitude = decimalFormat.format(data.getMag());

        TextView mag = (TextView) listItemView.findViewById(R.id.magnitude);
        mag.setText(magnitude);

        GradientDrawable magnitudeCircle = (GradientDrawable) mag.getBackground();
        int magnitudeColor = getMagnitudeColor(data.getMag());

        magnitudeCircle.setColor(magnitudeColor);

        TextView locOffset = (TextView) listItemView.findViewById(R.id.location_offset);
        locOffset.setText(locationOffset);

        TextView primaryLoc = (TextView) listItemView.findViewById(R.id.location);
        primaryLoc.setText(primaryLocation);

        Date date = new Date(data.getTime());

        SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
        String formattedDate = format.format(date);

        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        String formattedTime = timeFormat.format(date);

        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        dateView.setText(formattedDate);

        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        timeView.setText(formattedTime);


        return listItemView;
    }

    private int getMagnitudeColor(double magnitude){
        int magnitudeColorResourceId;
        int mag = (int) Math.floor(magnitude);

        switch (mag){
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
