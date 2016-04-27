package com.bryceadams1393gmail.mctourone;

import android.content.Context;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;



public class MCTOUR extends FragmentActivity implements LocationListener {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
  //  private HashMap<Marker, MyMarker> mMarkersHashMap;
  //  private ArrayList<MyMarker> mMyMarkersArray = new ArrayList<MyMarker>();

    LocationManager locationManager;
    String provider;

    private static final LatLng Huff_Athletic_Center = new LatLng(40.913678, -90.638799);
    private static final LatLng C_S_B = new LatLng(40.912738, -90.639803);
    private static final LatLng Stock_dale = new LatLng(40.913813, -90.637028);
    private static final LatLng Hewes_Library = new LatLng(40.914770, -90.637305);
    private static final LatLng Bowers_Hall = new LatLng(40.913442, -90.640226);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mctour);
        setUpMapIfNeeded();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        provider = locationManager.getBestProvider(new Criteria(), false);

        Location location = locationManager.getLastKnownLocation(provider);

        if (location != null) {

            onLocationChanged(location);

        }

        setUpMap();

        //plotMarkers(mMyMarkersArray);


    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();

            locationManager.requestLocationUpdates(provider, 400, 1, this);
    }


    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
                mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter(){

                    @Override
                    public View getInfoWindow(Marker arg0){
                        return null;
                    }
                    @Override
                    public View getInfoContents(Marker marker){
                        View v = getLayoutInflater().inflate(R.layout.infowindow_layout,null);
                        TextView tvLocality = (TextView)v.findViewById(R.id.tv_locality);
                        TextView tvSnippet = (TextView)v.findViewById(R.id.tv_snippet);

                        tvLocality.setText(marker.getTitle());
                        tvSnippet.setText(marker.getSnippet());

                        return v;
                    }
                });

            }

        /**
        * Type of map
         */
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


    }
    }

    private void setUpMap() {
        /**
         * Add a marker to the Huff
         */
        mMap.addMarker(new MarkerOptions().position(Huff_Athletic_Center).title("Huff Athletic Center" + "\n" + "The Huff Athletic Center, which opened in the fall of 2003, is a comprehensive facility for sports, fitness and recreation. The $22 million, 155,000-square-foot complex is built around the college's original 1925 gymnasium and the more recent Glennie Gymnasium.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        /**
         * Add a marker to the Center for Science and Business
         */
        mMap.addMarker(new MarkerOptions().position(C_S_B).title("Center for Science and Business" + "\n" + "The Center for Science and Business is a 138,000-square-foot structure. The innovative facility is designed to promote interaction among what have been traditionally independent departments, so that students who are able to understand the principles of both business and science will be better prepared for the increasingly demanding challenges of a global economy.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        /**
         * Add a marker to Stockdale Student Center
         */

        mMap.addMarker(new MarkerOptions().position(Stock_dale).title("Stockdale Student Center" + "\n" + "There are a variety of food options spread around campus. The Scots Dining Hall, Scotland Yard and Scots Market are all located in the Stockdale Student Center")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        /**
         * Add a marker to the Hewes Library
         */
        mMap.addMarker(new MarkerOptions().position(Hewes_Library).title("Hewes Library" + "\n" + "Completed in 1970 and renovated in 2003, the library collections include over 350,000 books, government documents, bound periodicals and videos/DVDs. Hewes Library is the oldest continuing federal depository in the state of Illinois starting in 1860 and is the 4th oldest in the nation. Monmouth now receives about 16% of what the government publishes.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        /**
         * Add a marker to Bowers Hall
         */
        mMap.addMarker(new MarkerOptions().position(Bowers_Hall).title("Bowers Hall" + "\n" + "Built in 2001, Bowers Hall is a modern, state-of-the-art residence hall. Constructed primarily for upper class students with suite style living. Four students share two bedroom rooms, a bathroom and a living room.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

    }


    @Override
    public void onLocationChanged(Location location) {

        Double lat = location.getLatitude();
        Double lng = location.getLongitude();

        mMap.clear();

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(lat, lng))
                .title("Your location")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 33/2));

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


    /**
     *   if the user does not have the app open on the
    * screen then the app will not update anything until
    * the user comes back to the map
     */

    @Override
    protected void onPause() {
        super.onPause();

        locationManager.removeUpdates(this);

    }


}
