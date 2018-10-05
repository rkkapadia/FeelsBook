package com.rkkapadi.feelsbook;

import android.text.Editable;

import java.util.Date;

public class JoyEmotion extends Emotions {
    private static final String emotion = "JOY" ;
//   // private static int joyCount;
//    public JoyEmotion(String comment, String date, int emotionCount)
//    {
//        super(comment, date);
//        //joyCount++;
//        emotionCount = this.getEmotionCount();
//        this.setEmotionCount(emotionCount++);
//    }

    public JoyEmotion(String comment, String date)
    {
        super(emotion, comment, date);
    }


}