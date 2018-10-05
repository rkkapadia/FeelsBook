package com.rkkapadi.feelsbook;

import android.app.Activity;
import android.content.Intent;
import android.os.TestLooperManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.IntStream;

public class MainActivity extends Activity implements View.OnClickListener {

    private ListView emotionListView;
    private EditText commentBox;
    private static final String FILENAME = "file.sav";
    private static final String FILENAME1 = "file1.sav";
    private boolean deleteClicked, editClicked = false;
    private String editString;

    private int totalCount;
    private TextView joyCounterText;
    private TextView angryCounterText;
    private TextView sadCounterText;
    private TextView surprisedCounterText;
    private TextView scaredCounterText;
    private TextView loveCounterText;
    private TextView totalCounterText;



    private int[] emotionCount = new int[6];

    private ArrayList<Emotions> emotionsList = new ArrayList<>();
    private ArrayAdapter<Emotions> adapter;
    private Date date = new Date();
    private boolean isJoy, isAngry, isSad, isScared, isSurprised, isLove = false;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);



        emotionListView = (ListView) findViewById(R.id.emotionListView);


        commentBox = (EditText) findViewById(R.id.commentBox);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);

        final Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(this);

        final TextView emotionStater = findViewById(R.id.EmotionStater);



        final Button editButton = findViewById(R.id.editButton);
        editButton.setOnClickListener(this);

        final Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);

        ImageButton joyButton = findViewById(R.id.joyButton);
        joyButton.setOnClickListener(this);

        ImageButton angryButton = findViewById(R.id.angryButton);
        angryButton.setOnClickListener(this);

        ImageButton surprisedButton = findViewById(R.id.surpriseButton);
        surprisedButton.setOnClickListener(this);

        ImageButton loveButton = findViewById(R.id.loveButton);
        loveButton.setOnClickListener(this);

        ImageButton sadButton = findViewById(R.id.sadButton);
        sadButton.setOnClickListener(this);

        ImageButton scaredButton = findViewById(R.id.scaredButton);
        scaredButton.setOnClickListener(this);

        emotionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Emotions current = emotionsList.get(position);

                emotionStater.setText(current.getEmotion());
                editString = current.getComment();
                commentBox.setText(editString);
                editButton.setVisibility(View.VISIBLE);
                cancelButton.setVisibility(View.VISIBLE);
                deleteButton.setVisibility(View.VISIBLE);

            }

        });

    }

    public void loadArrayfromFile()
    {
        try {
            FileInputStream fis = openFileInput(FILENAME1);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            String arrayString = in.readLine();
            String[] arrayString1 = arrayString.split(",");
            emotionCount[0] = Integer.parseInt(arrayString1[0]);
            emotionCount[1] = Integer.parseInt(arrayString1[1]);
            emotionCount[2] = Integer.parseInt(arrayString1[2]);
            emotionCount[3] = Integer.parseInt(arrayString1[3]);
            emotionCount[4] = Integer.parseInt(arrayString1[4]);
            emotionCount[5] = Integer.parseInt(arrayString1[5]);
            totalCount = Integer.parseInt(arrayString1[6]);
        } catch (FileNotFoundException e) {
            int[] emotionCount = new int[6];
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void onClick(View v)
    {
        TextView emotionStateText = findViewById(R.id.EmotionStater);
        EditText commentBox = findViewById(R.id.commentBox);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);

        Button editButton = findViewById(R.id.editButton);
        editButton.setOnClickListener(this);

        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(this);

        Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);

        TextView emotionStater = findViewById(R.id.EmotionStater);

        String currentEmotion;





        switch (v.getId())
        {
            case R.id.joyButton:
                //TODO
                isJoy = true;
                currentEmotion = "JOY";
                emotionStateText.setVisibility(View.VISIBLE);
                emotionStateText.setText("You are feeling " + currentEmotion + ". Save?");
                commentBox.setVisibility(View.VISIBLE);
                saveButton.setVisibility(View.VISIBLE);
                saveButton.setClickable(true);
                cancelButton.setVisibility(View.VISIBLE);
                cancelButton.setClickable(true);
                Log.d("joy", "clicked");

                emotionListView.setVisibility(View.INVISIBLE);
                findViewById(R.id.joyButton).setClickable(false);
                findViewById(R.id.angryButton).setClickable(false);
                findViewById(R.id.surpriseButton).setClickable(false);
                findViewById(R.id.sadButton).setClickable(false);
                findViewById(R.id.loveButton).setClickable(false);
                findViewById(R.id.scaredButton).setClickable(false);


                break;

            case R.id.angryButton:
                //TODO
                currentEmotion = "ANGRY";
                isAngry = true;
                emotionStateText.setVisibility(View.VISIBLE);
                emotionStateText.setText("You are feeling " + currentEmotion + ". Save?");
                commentBox.setVisibility(View.VISIBLE);
                saveButton.setVisibility(View.VISIBLE);
                saveButton.setClickable(true);
                cancelButton.setVisibility(View.VISIBLE);
                cancelButton.setClickable(true);

                //set clickable to false for previous view
                emotionListView.setVisibility(View.INVISIBLE);
                findViewById(R.id.joyButton).setClickable(false);
                findViewById(R.id.angryButton).setClickable(false);
                findViewById(R.id.surpriseButton).setClickable(false);
                findViewById(R.id.sadButton).setClickable(false);
                findViewById(R.id.loveButton).setClickable(false);
                findViewById(R.id.scaredButton).setClickable(false);



                break;

            case R.id.surpriseButton:
                //TODO
                currentEmotion = "SURPRISED";
                isSurprised = true;
                emotionStateText.setVisibility(View.VISIBLE);
                emotionStateText.setText("You are feeling " + currentEmotion + ". Save?");
                commentBox.setVisibility(View.VISIBLE);
                saveButton.setVisibility(View.VISIBLE);
                saveButton.setClickable(true);
                cancelButton.setVisibility(View.VISIBLE);
                cancelButton.setClickable(true);

                emotionListView.setVisibility(View.INVISIBLE);
                findViewById(R.id.joyButton).setClickable(false);
                findViewById(R.id.angryButton).setClickable(false);
                findViewById(R.id.surpriseButton).setClickable(false);
                findViewById(R.id.sadButton).setClickable(false);
                findViewById(R.id.loveButton).setClickable(false);
                findViewById(R.id.scaredButton).setClickable(false);



                break;

            case R.id.loveButton:
                //TODO
                currentEmotion = "LOVE";
                isLove = true;
                emotionStateText.setVisibility(View.VISIBLE);
                emotionStateText.setText("You are feeling " + currentEmotion + ". Save?");
                commentBox.setVisibility(View.VISIBLE);
                saveButton.setVisibility(View.VISIBLE);
                saveButton.setClickable(true);
                cancelButton.setVisibility(View.VISIBLE);
                cancelButton.setClickable(true);

                emotionListView.setVisibility(View.INVISIBLE);
                findViewById(R.id.joyButton).setClickable(false);
                findViewById(R.id.angryButton).setClickable(false);
                findViewById(R.id.surpriseButton).setClickable(false);
                findViewById(R.id.sadButton).setClickable(false);
                findViewById(R.id.loveButton).setClickable(false);
                findViewById(R.id.scaredButton).setClickable(false);



                break;

            case R.id.sadButton:
                //TODO
                currentEmotion = "SAD";
                isSad = true;
                emotionStateText.setVisibility(View.VISIBLE);
                emotionStateText.setText("You are feeling " + currentEmotion + ". Save?");
                commentBox.setVisibility(View.VISIBLE);
                saveButton.setVisibility(View.VISIBLE);
                saveButton.setClickable(true);
                cancelButton.setVisibility(View.VISIBLE);
                cancelButton.setClickable(true);

                emotionListView.setVisibility(View.INVISIBLE);
                findViewById(R.id.joyButton).setClickable(false);
                findViewById(R.id.angryButton).setClickable(false);
                findViewById(R.id.surpriseButton).setClickable(false);
                findViewById(R.id.sadButton).setClickable(false);
                findViewById(R.id.loveButton).setClickable(false);
                findViewById(R.id.scaredButton).setClickable(false);

                break;

            case R.id.scaredButton:
                //TODO
                currentEmotion = "SCARED";
                isScared = true;
                emotionStateText.setVisibility(View.VISIBLE);
                emotionStateText.setText("You are feeling " + currentEmotion + ". Save?");
                commentBox.setVisibility(View.VISIBLE);
                saveButton.setVisibility(View.VISIBLE);
                saveButton.setClickable(true);
                cancelButton.setVisibility(View.VISIBLE);
                cancelButton.setClickable(true);

                emotionListView.setVisibility(View.INVISIBLE);
                findViewById(R.id.joyButton).setClickable(false);
                findViewById(R.id.angryButton).setClickable(false);
                findViewById(R.id.surpriseButton).setClickable(false);
                findViewById(R.id.sadButton).setClickable(false);
                findViewById(R.id.loveButton).setClickable(false);
                findViewById(R.id.scaredButton).setClickable(false);

                break;

            case R.id.cancelButton:
                emotionStateText.setVisibility(View.INVISIBLE);
                commentBox.setVisibility(View.INVISIBLE);
                saveButton.setVisibility(View.INVISIBLE);
                editButton.setVisibility(View.INVISIBLE);
                deleteButton.setVisibility(View.INVISIBLE);
                saveButton.setClickable(false);
                commentBox.getText().clear();
                cancelButton.setVisibility(View.INVISIBLE);
                cancelButton.setClickable(false);

                emotionListView.setVisibility(View.VISIBLE);
                findViewById(R.id.joyButton).setClickable(true);
                findViewById(R.id.angryButton).setClickable(true);
                findViewById(R.id.surpriseButton).setClickable(true);
                findViewById(R.id.sadButton).setClickable(true);
                findViewById(R.id.loveButton).setClickable(true);
                findViewById(R.id.scaredButton).setClickable(true);



                break;

            case R.id.saveButton:

                if(isJoy)
                {
                    JoyEmotion joyEmotion = new JoyEmotion(commentBox.getText().toString(), formatDate(date));
                    emotionsList.add(joyEmotion);
                    emotionCount[0]++;
                    Log.d("joy", String.valueOf(emotionCount[0]));
                    joyEmotion.setEmotionCount(emotionCount[0]);
                    Log.d("joy", String.valueOf(emotionCount[0]));
                    adapter.notifyDataSetChanged();
                    joyCounterText = findViewById(R.id.joyCounterText);
                    joyCounterText.setText(String.valueOf(emotionCount[0]));
                    saveToFile();
                    isJoy = false;


                }
                if(isAngry)
                {
                    AngryEmotion angryEmotion = new AngryEmotion(commentBox.getText().toString(),formatDate(date));
                    emotionsList.add(angryEmotion);
                    emotionCount[1]++;
                    angryEmotion.setEmotionCount(emotionCount[1]);
                    adapter.notifyDataSetChanged();
                    angryCounterText =findViewById(R.id.angryCountText);
                    angryCounterText.setText(String.valueOf(emotionCount[1]));
                    saveToFile();
                    isAngry = false;

                    Log.d("angry", angryEmotion.toString());
                }
                if(isSurprised)
                {
                    SurprisedEmotion surprisedEmotion  = new SurprisedEmotion(commentBox.getText().toString(), formatDate(date));
                    emotionsList.add(surprisedEmotion);
                    emotionCount[2]++;
                    surprisedEmotion.setEmotionCount(emotionCount[2]);
                    adapter.notifyDataSetChanged();
                    surprisedCounterText =findViewById(R.id.surprisedCountText);
                    surprisedCounterText.setText(String.valueOf(emotionCount[2]));
                    saveToFile();
                    isSurprised=false;

                    Log.d("surprised", surprisedEmotion.toString());

                }
                if(isLove)
                {
                    LoveEmotion loveEmotion = new LoveEmotion(commentBox.getText().toString(),formatDate(date));
                    emotionsList.add(loveEmotion);
                    emotionCount[3]++;
                    loveEmotion.setEmotionCount(emotionCount[3]);
                    adapter.notifyDataSetChanged();
                    loveCounterText =findViewById(R.id.loveCountText);
                    loveCounterText.setText(String.valueOf(emotionCount[3]));
                    saveToFile();
                    isLove=false;

                    Log.d("love", loveEmotion.toString());
                }
                if(isSad)
                {
                    SadEmotion sadEmotion = new SadEmotion(commentBox.getText().toString(),formatDate(date));
                    emotionsList.add(sadEmotion);
                    emotionCount[4]++;
                    sadEmotion.setEmotionCount(emotionCount[4]);
                    adapter.notifyDataSetChanged();
                    sadCounterText =findViewById(R.id.sadCountText);
                    sadCounterText.setText(String.valueOf(emotionCount[4]));
                    saveToFile();
                    isSad=false;

                    Log.d("sad", sadEmotion.toString());
                }
                if(isScared)
                {
                    ScaredEmotion scaredEmotion = new ScaredEmotion(commentBox.getText().toString(),formatDate(date));
                    emotionsList.add(scaredEmotion);
                    emotionCount[5]++;
                    scaredEmotion.setEmotionCount(emotionCount[5]);
                    adapter.notifyDataSetChanged();
                    scaredCounterText =findViewById(R.id.scaredCountText);
                    scaredCounterText.setText(String.valueOf(emotionCount[5]));
                    saveToFile();
                    isScared=false;

                    Log.d("scared", scaredEmotion.toString());
                }

                emotionStateText.setVisibility(View.INVISIBLE);
                commentBox.setVisibility(View.INVISIBLE);
                commentBox.getText().clear();
                saveButton.setVisibility(View.INVISIBLE);
                saveButton.setClickable(false);
                cancelButton.setVisibility(View.INVISIBLE);
                cancelButton.setClickable(false);

                emotionListView.setVisibility(View.VISIBLE);
                findViewById(R.id.joyButton).setClickable(true);
                findViewById(R.id.angryButton).setClickable(true);
                findViewById(R.id.surpriseButton).setClickable(true);
                findViewById(R.id.sadButton).setClickable(true);
                findViewById(R.id.loveButton).setClickable(true);
                findViewById(R.id.scaredButton).setClickable(true);


               // startActivity(intent);
                Log.d("joyCount", String.valueOf(emotionCount[0]));
                totalCounterText=findViewById(R.id.totalCount);
                totalCount = sum(emotionCount);
                totalCounterText.setText(String.valueOf(totalCount));
                saveArrayToFile();

                break;
            case R.id.editButton:
                editClicked=true;
                emotionStater.setVisibility(View.VISIBLE);
                commentBox.setVisibility(View.VISIBLE);
                emotionListView.setVisibility(View.INVISIBLE);


        }
    }

    public void saveArrayToFile()
    {
        try{
            FileOutputStream fos = openFileOutput(FILENAME1, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            writer.write(String.valueOf(emotionCount[0])+",");
            writer.write(String.valueOf(emotionCount[1])+",");
            writer.write(String.valueOf(emotionCount[2])+",");
            writer.write(String.valueOf(emotionCount[3])+",");
            writer.write(String.valueOf(emotionCount[4])+",");
            writer.write(String.valueOf(emotionCount[5])+",");
            writer.write(String.valueOf(totalCount));
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }catch (IOException e){
            throw  new RuntimeException(e);
        }
    }


    public int sum(int[] emotionCount)
    {
      return emotionCount[0]+emotionCount[1]+emotionCount[2]+emotionCount[3]+emotionCount[4]+emotionCount[5];
    }
    @Override
    protected void onStart()
    {
        Log.d("App","OnStart");
        super.onStart();
        loadArrayfromFile();
        loadFromFile();
        adapter = new ArrayAdapter<Emotions>(this, R.layout.custom_list, emotionsList);
        emotionListView.setAdapter(adapter);

    }

    private void saveToFile()
    {
        try{
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(emotionsList, writer);
            writer.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }catch (IOException e){
            throw  new RuntimeException(e);
        }
    }

    private void loadFromFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<JoyEmotion>>() {
            }.getType();
            emotionsList = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            emotionsList = new ArrayList<Emotions>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String formatDate(Date date)
    {
        //2018-09-01T18:30:00
        String pattern = "yyyy-MM-dd'T'HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String formattedDate = simpleDateFormat.format(date);
        return formattedDate;
    }
}

