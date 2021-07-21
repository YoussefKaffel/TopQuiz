package youssef.kaffel.topquiz.Modele;

import java.util.List;

public class Question {
    private String mQuestion;
    private List<String> mChoiceList;
    private int mAnswerIndex;

    public  Question(String question,List<String> choiceList,int answerIndex){
        this.setQuestion (question);
        this.setChoicelist(choiceList);
        this.setAnswerIndex(answerIndex);
    }

    public void setQuestion(String Question) {
        this.mQuestion = Question;
    }

    public void setChoicelist(List<String> ChoiceList) {
        this.mChoiceList = ChoiceList;
    }

    public void setAnswerIndex(int AnswerIndex) {
        this.mAnswerIndex = AnswerIndex;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public List<String> getChoiceList() {
        return mChoiceList;
    }

    public int getAnswerIndex() {
        return mAnswerIndex;
    }
}
