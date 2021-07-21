package youssef.kaffel.topquiz.Modele;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class QuestionBank {
    private List<Question> mQuestionList;
    private int mNextQuestionIndex = 0;
    public QuestionBank(List<Question> questionList){
        mQuestionList=questionList;
        Collections.shuffle(mQuestionList);


    }
    public Question getQuestion(){
        if(mNextQuestionIndex==mQuestionList.size()){
            mNextQuestionIndex=0;
        }
        return mQuestionList.get(mNextQuestionIndex++);
    }
}
