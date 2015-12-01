package com.example.myatminmaung.timer;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
public class MainActivity extends AppCompatActivity{

    Button stopAlert, start, pause, resume, reset;
    NumberPicker numberRollMin,numberRollHr,numberRollSec;
    ProgressBar timeProgress;
    TextView time,textHr,textMin,textSec;
    MediaPlayer playClip;
    NotificationManager notimanager;
    CounterClass timer;
    long totalTime;
    boolean resetGuard=false;
    int hou,minu,secd,timeMax, progressCounter=0;
    private static final int RESULT_SETTINGS = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //*** Declaring and Connecting ID ***//
        // Button id is connected
        stopAlert=(Button)findViewById(R.id.stopRing);
        start=(Button)findViewById(R.id.buttonStart);
        pause=(Button)findViewById(R.id.buttonPause);
        resume=(Button)findViewById(R.id.buttonResume);
        reset=(Button)findViewById(R.id.buttonReset);

        //Visibility for button
        pause.setVisibility(View.INVISIBLE);
        resume.setVisibility(View.INVISIBLE);
        reset.setVisibility(View.INVISIBLE);


        //ProgressBar ID
        timeProgress=(ProgressBar)findViewById(R.id.progressBar);

        //TextView id is connected
        time=(TextView)findViewById(R.id.timeText);
        textHr=(TextView)findViewById(R.id.hourstext);
        textMin=(TextView)findViewById(R.id.mintext);
        textSec=(TextView)findViewById(R.id.sectext);


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
                minu = numberRollMin.getValue();
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

        //*** Button Listners ***//
        //Start listner
        start.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        //resetGuard set to true
                        resetGuard = true;

                        //Invisible for EditTexts
                        numberRollHr.setVisibility(View.INVISIBLE);
                        numberRollMin.setVisibility(View.INVISIBLE);
                        numberRollSec.setVisibility(View.INVISIBLE);
                        textHr.setVisibility(View.INVISIBLE);
                        textMin.setVisibility(View.INVISIBLE);
                        textSec.setVisibility(View.INVISIBLE);

                        //Visibility for Button
                        start.setVisibility(View.INVISIBLE);
                        pause.setVisibility(View.VISIBLE);
                        reset.setVisibility(View.VISIBLE);

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

                    }
                }

        );

        //pause listner
        pause.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        //Visibility for button
                        pause.setVisibility(View.INVISIBLE);
                        resume.setVisibility(View.VISIBLE);

                        timer.cancel();
                    }
                }

        );

        //resume listner
        resume.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {


                        //Visibility for button
                        pause.setVisibility(View.VISIBLE);
                        resume.setVisibility(View.INVISIBLE);

                        timer = new CounterClass(totalTime, 1000);
                        timer.start();
                    }
                }

        );

        //reset listner
        reset.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        if(resetGuard){
                            timer.cancel();
                        }

                        //Visibility for button
                        start.setVisibility(View.VISIBLE);
                        pause.setVisibility(View.INVISIBLE);
                        resume.setVisibility(View.INVISIBLE);
                        reset.setVisibility(View.INVISIBLE);
                        textHr.setVisibility(View.VISIBLE);
                        textMin.setVisibility(View.VISIBLE);
                        textSec.setVisibility(View.VISIBLE);


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
                }

        );



        //StopAlert listner
        stopAlert.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        //Visibility for Button
                        stopAlert.setVisibility(View.INVISIBLE);
                        pause.setVisibility(View.INVISIBLE);
                        resume.setVisibility(View.INVISIBLE);
                        start.setVisibility(View.VISIBLE);
                        reset.setVisibility(View.VISIBLE);
                        reset.setVisibility(View.INVISIBLE);

                        //Visibility
                        numberRollHr.setVisibility(View.VISIBLE);
                        numberRollMin.setVisibility(View.VISIBLE);
                        numberRollSec.setVisibility(View.VISIBLE);
                        textHr.setVisibility(View.VISIBLE);
                        textMin.setVisibility(View.VISIBLE);
                        textSec.setVisibility(View.VISIBLE);


                        //Delete Notification
                        notimanager.cancel(1);
                        notimanager.cancel(2);

                        //Set NumberPicker
                        numberRollHr.setValue(0);
                        numberRollMin.setValue(0);
                        numberRollSec.setValue(0);


                        //Text Color
                        time.setTextColor(Color.BLACK);

                        playClip.stop();
                    }
                }

        );

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
            timeProgress.setVisibility(View.INVISIBLE);

            //Visibility for button
            stopAlert.setVisibility(View.VISIBLE);
            pause.setVisibility(View.INVISIBLE);
            resume.setVisibility(View.INVISIBLE);
            start.setVisibility(View.INVISIBLE);
            reset.setVisibility(View.INVISIBLE);
            reset.setVisibility(View.INVISIBLE);

            //Empty ProgressBar
            progressCounter=0;

            //Setting Up AlertSound
            //Preference....
            showUserSettings();
            playClip.start();

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
                    .setSmallIcon(R.drawable.hourglass)
                    .setContentTitle("Cooking Done in: " + hms);

            Bitmap picture= BitmapFactory.decodeResource(getResources(), R.drawable.hourglass);
            notiStatus.setLargeIcon(picture);

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

        String favTone= sharedPrefs.getString("prefSyncFrequency", "");

        switch (favTone){

            case "musicbox":
                playClip = MediaPlayer.create(MainActivity.this,R.raw.musicbox);
                break;
            case "prelude":
                playClip = MediaPlayer.create(MainActivity.this,R.raw.prelude);
                break;
            case "loudalarm":
                playClip = MediaPlayer.create(MainActivity.this,R.raw.loudalarm);
                break;
            case "tornadosiren":
                playClip = MediaPlayer.create(MainActivity.this,R.raw.tornadosiren);
                break;
            default:
                playClip = MediaPlayer.create(MainActivity.this,R.raw.loudalarm);
        }

   }

}


