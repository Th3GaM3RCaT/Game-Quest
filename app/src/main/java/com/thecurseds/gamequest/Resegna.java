package com.thecurseds.gamequest;

public class Resegna {
    private int reviewer;
    private String reviewedId;
    private String review;
    private int assessment;


    public Resegna() {
        this.reviewer = 0;
        this.reviewedId = "";
        this.review = "";
        this.assessment = 0;
    }
    public Resegna(int reviewer, String reviewed, String review, int assessment) {
        this.reviewer = reviewer;
        this.reviewedId = reviewed;
        this.review = review;
        this.assessment = assessment;
    }
    public int getReviewer() {
        return reviewer;
    }
    public void setReviewer(int reviewer) {
        this.reviewer = reviewer;
    }
    public String getReviewedId() {
        return reviewedId;
    }
    public void setReviewedId(String reviewedId) {
        this.reviewedId = reviewedId;
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
