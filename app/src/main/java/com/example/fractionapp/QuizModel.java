package com.example.fractionapp;

public class QuizModel {
    private String question_nominator, question_denominator;
    private String option1_text1,option1_text2,option2_text1,option2_text2,option3_text1,option3_text2,option4_text1,option4_text2;
    private String answer;

    public QuizModel(String question_nominator, String question_denominator, String option1_text1, String option1_text2, String option2_text1, String option2_text2, String option3_text1, String option3_text2, String option4_text1, String option4_text2, String answer) {
        this.question_nominator = question_nominator;
        this.question_denominator = question_denominator;
        this.option1_text1 = option1_text1;
        this.option1_text2 = option1_text2;
        this.option2_text1 = option2_text1;
        this.option2_text2 = option2_text2;
        this.option3_text1 = option3_text1;
        this.option3_text2 = option3_text2;
        this.option4_text1 = option4_text1;
        this.option4_text2 = option4_text2;
        this.answer = answer;
    }

    public String getQuestion_nominator() {
        return question_nominator;
    }

    public void setQuestion_nominator(String question_nominator) {
        this.question_nominator = question_nominator;
    }

    public String getQuestion_denominator() {
        return question_denominator;
    }

    public void setQuestion_denominator(String question_denominator) {
        this.question_denominator = question_denominator;
    }

    public String getOption1_text1() {
        return option1_text1;
    }

    public void setOption1_text1(String option1_text1) {
        this.option1_text1 = option1_text1;
    }

    public String getOption1_text2() {
        return option1_text2;
    }

    public void setOption1_text2(String option1_text2) {
        this.option1_text2 = option1_text2;
    }

    public String getOption2_text1() {
        return option2_text1;
    }

    public void setOption2_text1(String option2_text1) {
        this.option2_text1 = option2_text1;
    }

    public String getOption2_text2() {
        return option2_text2;
    }

    public void setOption2_text2(String option2_text2) {
        this.option2_text2 = option2_text2;
    }

    public String getOption3_text1() {
        return option3_text1;
    }

    public void setOption3_text1(String option3_text1) {
        this.option3_text1 = option3_text1;
    }

    public String getOption3_text2() {
        return option3_text2;
    }

    public void setOption3_text2(String option3_text2) {
        this.option3_text2 = option3_text2;
    }

    public String getOption4_text1() {
        return option4_text1;
    }

    public void setOption4_text1(String option4_text1) {
        this.option4_text1 = option4_text1;
    }

    public String getOption4_text2() {
        return option4_text2;
    }

    public void setOption4_text2(String option4_text2) {
        this.option4_text2 = option4_text2;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
