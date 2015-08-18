package com.androbos.tes.uimap;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.androbos.tes.BaseActivity;
import com.androbos.tes.R;
import com.androbos.tes.model.Location;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class AddRouteActivity extends BaseActivity {

    public static final int ADD_MAP_KEY = 1;

    @InjectView(R.id.etFrom)
    EditText etFrom;
    @InjectView(R.id.spinnerDestination)
    Spinner spDestination;

    private double lat, lng;
    private ArrayAdapter<Location> locationArrayAdapter;
    private List<Location> locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_route);
        ButterKnife.inject(this);

        getSupportActionBar().setTitle("Generator Rute");
        locations = new ArrayList<>();
        generateLocationDummy();
        locationArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, locations);
        spDestination.setAdapter(locationArrayAdapter);

    }

    /**
     * hekz!
     *
     *
     * Hardcode doloe gan..
     * forgive me ~
     */
    private void generateLocationDummy() {
        locations.add(new Location("lokasi 1", -8.146982, 110.6076521));
        locations.add(new Location("lokasi 2", -8.1235303, 110.5161231));
        locations.add(new Location("lokasi 3", -8.1501091, 110.6121113));
        locations.add(new Location("lokasi 4", -7.8017879,110.1318019));
    }

    @OnClick(R.id.btnAddMap)
    public void bntAddMap() {
        startActivityForResult(new Intent(this, AddMapActivity.class), ADD_MAP_KEY);
    }

    @OnClick(R.id.btnGetRoute)
    public void getRoute() {
        if (!TextUtils.isEmpty(etFrom.getText())) {
            Location loc = locations.get(spDestination.getSelectedItemPosition());
            Bundle data = new Bundle();
            data.putDouble("latAwal", lat);
            data.putDouble("lngAwal", lng);
            data.putDouble("latTujuan", loc.getLat());
            data.putDouble("lngTujuan", loc.getLng());
            data.putInt("status", MainUiActivity.FROM_NET);
            data.putString("tujuan", loc.getName());

            startActivity(new Intent(this, ViewRouteActivity.class).putExtras(data));
        } else {
            Toast.makeText(this, "can't empty", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_MAP_KEY) {
            if (resultCode == RESULT_OK) {
                lat = data.getExtras().getDouble("lat");
                lng = data.getExtras().getDouble("lng");
                etFrom.setText("" + lat + "," + lng);
            }
        }
    }
}
