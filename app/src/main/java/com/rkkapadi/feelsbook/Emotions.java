package com.rkkapadi.feelsbook;

public abstract class Emotions extends Object
{

    private String comment;
    private int emotionCount;
    private String date;
    private String emotion;
//    public Emotions(String comment, Date date, int emotionCount)
//    {
//        this.setComment(comment);
//        this.date = date;
//        this.emotionCount = emotionCount;
//    }
//
    public Emotions(String emotion, String comment, String date, int emotionCount)
    {
        this.setComment(comment);
        this.emotion = emotion;
        this.date = date;
        this.emotionCount = emotionCount;
    }

    public Emotions(String emotion, String comment, String date)
    {
        this.setComment(comment);
        this.emotion = emotion;
        this.date = date;
    }

    public String getComment()
    {
        return comment;
    }

    public String getEmotion()
    {
        return emotion;
    }

    public void setEmotion(String emotion)
    {
        this.emotion = emotion;
    }


    public void setComment(String comment) throws CommentTooLongException {
        if (comment.length() <= 100) {
            this.comment = comment;
        } else {
            throw new CommentTooLongException();
        }
    }

    public String getDate() {
;        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getEmotionCount() {
        return emotionCount;
    }

    public void setEmotionCount(int emotionCount) {
        this.emotionCount = emotionCount;
    }

    public void incrementCount(int emotionCount)
    {
        emotionCount = emotionCount + 1;
    }



    @Override
    public String toString()
    {
        return  emotion + " | " + date + " | " + comment;
    }
}
