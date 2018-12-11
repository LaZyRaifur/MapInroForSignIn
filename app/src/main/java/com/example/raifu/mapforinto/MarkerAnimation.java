package com.example.raifu.mapforinto;

import android.graphics.Interpolator;
import android.os.SystemClock;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.logging.Handler;

public class MarkerAnimation {

    public static void animateMarkerToGB(final Marker marker, final LatLng finalPosition,final LatLngInterpolartor latLngInterpolartor){

        final LatLng startPosition = marker.getPosition();
        final android.os.Handler handler = new android.os.Handler();
        final long start = SystemClock.uptimeMillis();
        final android.view.animation.Interpolator interpolator = new AccelerateDecelerateInterpolator();
        final float durationInMs = 2000;

        handler.post(new Runnable() {

            long elapsed;
            float t;
            float v;

            @Override
            public void run() {

                //calculate progress using interpolator
                elapsed = SystemClock.uptimeMillis() - start;
                t = elapsed/durationInMs;
                v = interpolator.getInterpolation(t);

                marker.setPosition(latLngInterpolartor.interpolate(v,startPosition,finalPosition));


                //Repeat till progress is complete
                if (t<1){
                    //post again 16ms later
                    handler.postDelayed(this,16);
                }
            }
        });
    }
}
