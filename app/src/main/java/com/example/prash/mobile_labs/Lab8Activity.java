package com.example.prash.mobile_labs;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Lab8Activity extends AppCompatActivity {
    private final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab8);
    }

    @Override
    protected void onResume(){
        super.onResume();
        updateLocation();
    }

    @Override
    protected void onPause() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.removeUpdates(locationListener);
        super.onPause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                TextView locationDisableText = (TextView) findViewById(R.id.location_disable);

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted
                    locationDisableText.setVisibility(View.GONE);
                } else {
                    // permission denied
                    locationDisableText.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void updateLocation() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(true);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(false);

        String bestProvider = locationManager.getBestProvider(criteria, true);

        String gpsProvider = LocationManager.GPS_PROVIDER;
        if (!locationManager.isProviderEnabled(gpsProvider)){
            String locConfig = Settings.ACTION_LOCATION_SOURCE_SETTINGS;
            Intent enableGPS = new Intent(locConfig);
            startActivity(enableGPS);
        }

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location != null) {
                    Log.d("LOCATION CHANGED", location.getLatitude() + "");
                    Log.d("LOCATION CHANGED", location.getLongitude() + "");
                    Toast.makeText(Lab8Activity.this, "Latitude = "+
                                    location.getLatitude() + "" +"Longitude = "+ location.getLongitude(),
                            Toast.LENGTH_LONG).show();
                    reverseGeocode(location.getLatitude(), location.getLongitude());
                } else {
                    Toast.makeText(Lab8Activity.this, "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            return;
        }
        locationManager.requestLocationUpdates(bestProvider, 20000, 0, locationListener);

        Toast.makeText(this, "GETTING LOCATION", Toast.LENGTH_SHORT).show();

    }

    private void reverseGeocode(double latitude, double longitude) {
        if (Geocoder.isPresent()) {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            try {
                List<Address> ls = geocoder.getFromLocation(latitude, longitude, 3);
                for (Address address : ls) {
                    String addrLine1 = address.getAddressLine(0);
                    String addrLine2 = address.getAddressLine(1);
                    String city = address.getLocality();
                    String province = address.getAdminArea();
                    String country = address.getCountryName();
                    String postalCode = address.getPostalCode();
                    String phoneNumber = address.getPhone();
                    String url = address.getUrl();
                    EditText addressLine1Edit = (EditText)findViewById(R.id.addressLine1);
                    EditText addressLine2Edit = (EditText)findViewById(R.id.addressLine2);
                    EditText cityEdit = (EditText)findViewById(R.id.city);
                    EditText provinceEdit = (EditText)findViewById(R.id.province);
                    EditText countryEdit = (EditText)findViewById(R.id.country);
                    EditText postalCodeEdit = (EditText)findViewById(R.id.postalCode);
                    EditText phoneNumberEdit = (EditText)findViewById(R.id.phoneNumber);
                    EditText urlEdit = (EditText)findViewById(R.id.url);
                    addressLine1Edit.setText(addrLine1);
                    addressLine2Edit.setText(addrLine2);
                    cityEdit.setText(city);
                    provinceEdit.setText(province);
                    countryEdit.setText(country);
                    postalCodeEdit.setText(postalCode);
                    phoneNumberEdit.setText(phoneNumber);
                    urlEdit.setText(url);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
