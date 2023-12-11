package com.thecurseds.gamequest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

public class Resegna {
    private Bitmap reviewer;
    private String review;
    private int assessment;

    public Resegna() {
        this.reviewer = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        this.review = "";
        this.assessment = 0;
    }

    public Resegna(Bitmap reviewer, String review, int assessment) {
        this.reviewer = reviewer;
        this.review = review;
        this.assessment = assessment;
    }
    public Bitmap getReviewer() {
        return reviewer;
    }
    public void setReviewer(Bitmap reviewer) {
        this.reviewer = reviewer;
    }
    public String getReview() {
        return review;
    }
    public void setReview(String review) {
        this.review = review;
    }
    public int getAssessment() {
        return assessment;
    }
    public void setAssessment(int assessment) {
        this.assessment = assessment;
    }
    
}
