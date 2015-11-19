package com.example.myatminmaung.timer;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.preference.PreferenceActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ProgressBar;
import java.util.concurrent.TimeUnit;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.CountDownTimer;
import android.app.NotificationManager;
import android.widget.ProgressBar;
import android.widget.NumberPicker;
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.support.v4.view.GestureDetectorCompat;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener {

    Button stopAlert;
    NumberPicker numberRollMin,numberRollHr,numberRollSec;
    ProgressBar timeProgress;
    TextView time,textButton;
    MediaPlayer stopClick;
    GestureDetectorCompat gestureDetect;
    NotificationManager notimanager;
    CounterClass timer;
    long totalTime;
    boolean resetGuard=false, startGuard=false, pauseGuard=false, resumeGuard=false;
    int hou,minu,secd,timeMax, progressCounter=0;
    private static final int RESULT_SETTINGS = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //*** Declaring and Connecting ID ***//
        // Button id is connected
        stopAlert=(Button)findViewById(R.id.stopRing);
        timeProgress=(ProgressBar)findViewById(R.id.progressBar);

        //TextView id is connected
        time=(TextView)findViewById(R.id.timeText);
        textButton=(TextView)findViewById(R.id.textStart);

        //NumberPicker id is connected
        numberRollHr=(NumberPicker)findViewById(R.id.numberPickerHR);
        numberRollMin=(NumberPicker)findViewById(R.id.numberPickerMIN);
        numberRollSec=(NumberPicker)findViewById(R.id.numberPickerSEC);

        //setRange numberRolls
        //Hr
        numberRollHr.setMaxValue(3);
        numberRollHr.setMinValue(0);

        //Min
        numberRollMin.setMaxValue(60);
        numberRollMin.setMinValue(0);

        //Sec
        numberRollSec.setMaxValue(60);
        numberRollSec.setMinValue(0);

        //GestureDetector
        this.gestureDetect= new GestureDetectorCompat(this,this);
        gestureDetect.setOnDoubleTapListener(this);

        //Invisible for Textview
        time.setVisibility(View.INVISIBLE);
        timeProgress.setVisibility(View.INVISIBLE);

        //Invisible Button
        stopAlert.setVisibility(View.INVISIBLE);

        //numRoll Listner for hrs
        numberRollHr.setOnValueChangedListener(new NumberPicker.
                OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int
                    oldVal, int newVal) {
                hou = numberRollHr.getValue();
            }
        });

        //numRoll Listner for mins
        numberRollMin.setOnValueChangedListener(new NumberPicker.
                OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int
                    oldVal, int newVal) {
                minu=numberRollMin.getValue();
            }
        });

        //numRoll Listner for mins
        numberRollSec.setOnValueChangedListener(new NumberPicker.
                OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int
                    oldVal, int newVal) {
                secd = numberRollSec.getValue();
            }
        });

        //StopAlert listner
        stopAlert.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        //Invisibility for Button
                        stopAlert.setVisibility(View.INVISIBLE);

                        //Visibility
                        numberRollHr.setVisibility(View.VISIBLE);
                        numberRollMin.setVisibility(View.VISIBLE);
                        numberRollSec.setVisibility(View.VISIBLE);
                        textButton.setVisibility(View.VISIBLE);

                        //StartGuard
                        startGuard=false;
                        pauseGuard=false;
                        resumeGuard=false;

                        //Set text to textButton
                        textButton.setText(">> Start >>");

                        //Delete Notification
                        notimanager.cancel(1);
                        notimanager.cancel(2);

                        //Set NumberPicker
                        numberRollHr.setValue(0);
                        numberRollMin.setValue(0);
                        numberRollSec.setValue(0);


                        //Text Color
                        time.setTextColor(Color.BLACK);

                        stopClick.stop();
                    }
                }

        );

        //Preference....
        showUserSettings();
    }
    /***CountDownClass override***/
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer{

        //Constructor
        public CounterClass(long millisInFuture, long countDownInterval){

            super(millisInFuture, countDownInterval);
        }

        //onFinish
        public void onFinish(){

            time.setTextColor(Color.BLACK);
            time.setText("Stop !!!");


            //Invisibility for button
            time.setVisibility(View.INVISIBLE);
            textButton.setVisibility(View.INVISIBLE);
            timeProgress.setVisibility(View.INVISIBLE);

            //Visibility for button
            stopAlert.setVisibility(View.VISIBLE);

            //Empty ProgressBar
            progressCounter=0;

            //Setting Up AlertSound
            stopClick = MediaPlayer.create(MainActivity.this,R.raw.loudalarm);
            stopClick.start();

            //Setting up Notification
            NotificationCompat.Builder notification= new NotificationCompat.Builder(MainActivity.this)
                    .setSmallIcon(R.drawable.creme_brelee)
                    .setContentTitle("Time is up!!!")
                    .setContentText("Stop Cooking :)");

            //Vibrate
            long [] vibrate= {0,100,200,300};
            notification.setVibrate(vibrate);

            //Notification Action
            PendingIntent myPendingIntent;
            Intent notiIntent = new Intent();
            Context myContext= getApplicationContext();

            notiIntent.setClass(myContext, MainActivity.class);
            notiIntent.putExtra("ID", 1);
            myPendingIntent= PendingIntent.getActivity(myContext,0,notiIntent,0);

            notification.setContentIntent(myPendingIntent);


            //Put notification in action
            notimanager= (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            notimanager.notify(1,notification.build());
        }

        //onTick
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @SuppressLint("NewApi")
        public void onTick(long millisUntilFinished){
            totalTime= millisUntilFinished;
            String hms= String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(totalTime),
                    TimeUnit.MILLISECONDS.toMinutes(totalTime) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(totalTime)),
                    TimeUnit.MILLISECONDS.toSeconds(totalTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(totalTime)));
            System.out.print(hms);

            //Progress Bar
            timeProgress=(ProgressBar)findViewById(R.id.progressBar);
            progressCounter+=1000;
            timeProgress.setMax(timeMax);
            timeProgress.setProgress(progressCounter);

            //***Setting up Notification
            NotificationCompat.Builder notiStatus= new NotificationCompat.Builder(MainActivity.this)
                    .setTicker(hms)
                    .setSmallIcon(R.drawable.creme_brelee)
                    .setContentTitle("Cooking Done in: " + hms);



            //Notification Action
            PendingIntent myPendingIntent;
            Intent notiIntent = new Intent();
            Context myContext= getApplicationContext();

            notiIntent.setClass(myContext, MainActivity.class);
            notiIntent.putExtra("ID", 2);
            myPendingIntent= PendingIntent.getActivity(myContext,0,notiIntent,0);

            notiStatus.setContentIntent(myPendingIntent);

            //Put notification in action
            notimanager= (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            notimanager.notify(2,notiStatus.build());

            //Text color 5s before time up!!
            if(totalTime<= 11000){
                time.setTextColor(Color.RED);

            }

            time.setText(hms);
        }
    }


    /***Gesture****/
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetect.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    //Resume
    @Override
    public boolean onDoubleTap(MotionEvent e) {

        if(!resumeGuard){

           /* Toast.makeText(getApplicationContext(), "Timer need to be Started",
                    Toast.LENGTH_SHORT).show();*/

            return true;
        }

        //Set text to textButton
        textButton.setText("> Pause <");

        //resumeGuard
        resumeGuard=true;

        timer = new CounterClass(totalTime,1000);
        timer.start();
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        //time.setText("onSingleTapConfirmed");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        //time.setText("onDoubleTapEvent");
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        //time.setText("onDown");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        //time.setText("onShowPress");

    }

    //Pause
    @Override
    public boolean onSingleTapUp(MotionEvent e) {

        if(!pauseGuard){

            /*Toast.makeText(getApplicationContext(), "Timer need to be Started",
                    Toast.LENGTH_SHORT).show();*/

            return true;
        }

        //set text to textbutton
        textButton.setText(">> Resume <<");

        //pauseGuard
        pauseGuard=true;

        timer.cancel();

        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        //time.setText("onScroll");
        return true;
    }

    //Reset Button
    @Override
    public void onLongPress(MotionEvent e) {

        if(resetGuard){
            timer.cancel();
        }

        //StartGuard
        startGuard=false;
        pauseGuard=false;
        resumeGuard=false;

        //Set text to textButton
        textButton.setText(">> Start >>");

        //Empty ProgressBar
        progressCounter=0;


        //Invisible for Textview
        time.setVisibility(View.INVISIBLE);
        timeProgress.setVisibility(View.INVISIBLE);

        //Visible for EditTexts
        numberRollHr.setVisibility(View.VISIBLE);
        numberRollMin.setVisibility(View.VISIBLE);
        numberRollSec.setVisibility(View.VISIBLE);

        //Text color
        time.setTextColor(Color.BLACK);

        //Notification cancel
        notimanager.cancel(2);

        //Set NumberPicker
        numberRollHr.setValue(0);
        numberRollMin.setValue(0);
        numberRollSec.setValue(0);

    }

    //Start Button
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        //resetGuard set to true
        if(!startGuard){
            resetGuard = true;
            startGuard= true;
            pauseGuard=true;
            resumeGuard=true;

            //Set text to textButton
            textButton.setText("> Pause <");

            //reset remainder
            Toast.makeText(getApplicationContext(), "Long Press to Reset",
                    Toast.LENGTH_SHORT).show();

            //Invisible for EditTexts
            numberRollHr.setVisibility(View.INVISIBLE);
            numberRollMin.setVisibility(View.INVISIBLE);
            numberRollSec.setVisibility(View.INVISIBLE);

            //Visible for TextView
            time.setVisibility(View.VISIBLE);
            timeProgress.setVisibility(View.VISIBLE);

            hou = hou * 3600000;
            minu = minu * 60000;
            secd = secd * 1000;

            totalTime = hou + minu + secd;
            timeMax= hou + minu + secd;

            //******* Setting timer(CHECK BACK AGAIN)
            if (hou >= 0 || minu >= 0 || secd >= 0) {
                //CounterClass
                timer = new CounterClass(totalTime, 1000);
            }
            timer.start();
            hou=minu=secd=0;
            return true;
        }

        Toast.makeText(getApplicationContext(), "Timer is already Started",
                Toast.LENGTH_SHORT).show();
        return true;
    }

    /*****End**/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        //Preference......
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);*/

        //Preference.....
        switch (item.getItemId()) {

            case R.id.menu_settings:
                Intent i = new Intent(this, UserSettingActivity.class);
                startActivityForResult(i, RESULT_SETTINGS);
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_SETTINGS:
                showUserSettings();
                break;

        }

    }

    private void showUserSettings() {
        SharedPreferences sharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this);

        String favTime= sharedPrefs.getString("prefSyncFrequency", "");

        numberRollMin.setValue(Integer.parseInt(favTime));
        minu=numberRollMin.getValue();

   }

}


