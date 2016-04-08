package com.techreviewsandhelp.getbacktowork;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

/**
public class MainActivity extends Activity{
    private SensorManager mySensorManager;

    private float xAccel;
    private float yAccel;
    private float zAccel;
    private float xPreviousAccel;
    private float yPreviousAccel;
    private float zPreviousAccel;

    private boolean firstUpdate = true;
    private boolean shakeInitiated = false;
    private final float shakeThreshold = 8f;
    private long lastUpdate = 0;
    private static final int SHAKE_THRESHOLD = 800;
    private float xNew, yNew, zNew;
    private SensorManager sensorMgr;

    MediaPlayer MediaPlayer = new MediaPlayer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abc);
        mySensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mySensorManager.registerListener(mySensorEventListener,
                mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);

    }

    private final SensorEventListener mySensorEventListener = new SensorEventListener(){
        public void onSensorChanged(SensorEvent se){
          //  updateAccelParameters(se.values[0], se.values[1], se.values[2]);
            if((!shakeInitiated) && isAccelerationChanged()){
                shakeInitiated = true;
            } else if ((shakeInitiated) && isAccelerationChanged()){
                executeShakeAction();
            } else if ((shakeInitiated) && !isAccelerationChanged()){
                shakeInitiated = false;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }
    };

    private void updateAccelParameters(int sensor, float[] values){
        if (sensor == SensorManager.SENSOR_ACCELEROMETER){
        long curTime = System.currentTimeMillis();
        // only allow one update every 3 seconds.
        if ((curTime - lastUpdate) > 3000) {
            long diffTime = (curTime - lastUpdate);
            lastUpdate = curTime;

            xNew = values[SensorManager.DATA_X];
            yNew = values[SensorManager.DATA_Y];
            zNew = values[SensorManager.DATA_Z];
            float speed = Math.abs(xNew+yNew+zNew - xPreviousAccel - yPreviousAccel - zPreviousAccel) / diffTime * 10000;
            if (speed > SHAKE_THRESHOLD) {
                // yes, this is a shake action! Do something about it!
            }

            /**

            if (firstUpdate){
                xPreviousAccel = xNew;
                yPreviousAccel = yNew;
                zPreviousAccel = zNew;
                firstUpdate = false;
            } else {

                xPreviousAccel = xAccel;
                yPreviousAccel = yAccel;
                zPreviousAccel = zAccel;
            }
**/
/**        xAccel = xNew;
        yAccel = yNew;
        zAccel = zNew;
    }}}

    private boolean isAccelerationChanged(){
        float deltaX = Math.abs(xPreviousAccel - xAccel);
        float deltaY = Math.abs(yPreviousAccel - yAccel);
        float deltaZ = Math.abs(zPreviousAccel - zAccel);
        return (deltaX > shakeThreshold && deltaY > shakeThreshold)
                || (deltaX > shakeThreshold && deltaZ > shakeThreshold)
                || (deltaY > shakeThreshold && deltaZ > shakeThreshold);
    }


    private void executeShakeAction(){

        if(MediaPlayer.isPlaying()){
            MediaPlayer.stop();
        }else{
            MediaPlayer player = MediaPlayer.create(this, R.raw.work);
            Toast.makeText(getApplicationContext(), "playing sound", Toast.LENGTH_SHORT).show();
            player.start();
        }

    }


}

**/

public class MainActivity extends Activity implements SensorListener {
    // For shake motion detection.
    private SensorManager sensorMgr;
    private long lastUpdate = -1;
    private float x, y, z;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 800;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.abc);
        // start motion detection
        sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        boolean accelSupported = sensorMgr.registerListener(this,
                SensorManager.SENSOR_ACCELEROMETER,
                SensorManager.SENSOR_DELAY_GAME);

        if (!accelSupported) {
            // on accelerometer on this device
            sensorMgr.unregisterListener(this,
                    SensorManager.SENSOR_ACCELEROMETER);
        }
    }



    public void onAccuracyChanged(int arg0, int arg1) {
        // TODO Auto-generated method stub
    }

    public void onSensorChanged(int sensor, float[] values) {
        if (sensor == SensorManager.SENSOR_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();
            // only allow one update every 3s.
            if ((curTime - lastUpdate) > 3000) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                x = values[SensorManager.DATA_X];
                y = values[SensorManager.DATA_Y];
                z = values[SensorManager.DATA_Z];
/**
 * I 'm not sure why, but when I don't have it looking at which way the device is being shaken, the
 * app ignores the 3 second rule.
 * I'm also not sure why, but when only use the z,x axis and make it so sensitive that it should dectect the
 * y axis. The app again ignores the 3 second rule.
 *
 * When I use code that isn't depreciated, the app again ignores the 3 second rule.
 *
 * Because of this, I'm leaving this as it is.
 *
 * Also, I'm not exactly sure what is causing it. But I also can't have this code looking at a string or text.
 * (I was going to have the app change a string, play the sound, wait 3 seconds, change the string again. And
 * have something in where if the string is 0 then do nothing)
 * While this didn't cause any errors, this did cause the app to stop working on the device.
 *
 * (As anyone can tell, I spent enough time on this and I have to say this is good enough for a free app)
 * I commented out some of the code I tried before for anyone that wants to see what happened.
 */

                if(Round(x,4)>100.0000){
                    Log.d("sensor", "X Right axis: " + x);
                   // Toast.makeText(this, "Right shake detected", Toast.LENGTH_SHORT).show();
                    MediaPlayer player = MediaPlayer.create(this, R.raw.work);
                 //   Toast.makeText(getApplicationContext(), "playing sound", Toast.LENGTH_SHORT).show();
                    player.start();
                }
                else if(Round(x,4)<-5.0000){
                    Log.d("sensor", "X Left axis: " + x);
                  //  Toast.makeText(this, "Left shake detected", Toast.LENGTH_SHORT).show();
                    MediaPlayer player = MediaPlayer.create(this, R.raw.work);
                 //   Toast.makeText(getApplicationContext(), "playing sound", Toast.LENGTH_SHORT).show();
                    player.start();
                }
                else if(Round(z,4)<-100.0000){
                    Log.d("sensor", "z Left axis: " + x);
                 //   Toast.makeText(this, "z shake detected", Toast.LENGTH_SHORT).show();
                    MediaPlayer player = MediaPlayer.create(this, R.raw.work);
                 //   Toast.makeText(getApplicationContext(), "playing sound", Toast.LENGTH_SHORT).show();
                    player.start();
                }
                else if(Round(z,4)>5.0000){
                    Log.d("sensor", "z Right axis: " + x);
                //    Toast.makeText(this, "Back shake detected", Toast.LENGTH_SHORT).show();
                    MediaPlayer player = MediaPlayer.create(this, R.raw.work);
                 //   Toast.makeText(getApplicationContext(), "playing sound", Toast.LENGTH_SHORT).show();
                    player.start();
                }

                float speed = Math.abs(x+y+z - last_x - last_y - last_z) / diffTime * 10000;

                // Log.d("sensor", "diff: " + diffTime + " - speed: " + speed);
                if (speed > SHAKE_THRESHOLD) {
                    //Log.d("sensor", "shake detected w/ speed: " + speed);
                    //Toast.makeText(this, "shake detected w/ speed: " + speed, Toast.LENGTH_SHORT).show();

                }
                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }

    public static float Round(float Rval, int Rpl) {
        float p = (float)Math.pow(10,Rpl);
        Rval = Rval * p;
        float tmp = Math.round(Rval);
        return (float)tmp/p;
    }
}