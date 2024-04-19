package elements;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import androidx.annotation.NonNull;


public class Question implements Parcelable {

    public String question;
    public int step; // En que posición sale en pantalla
    public ArrayList<Response> responses;

    public Question(String question, ArrayList<Response> responses, int step) {
        this.question = question;
        this.responses = responses;
        this.step=step;
    }

    protected Question(Parcel in) {
        question = in.readString();
        step = in.readInt();
    }


    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<Response> getResponses() {
        return responses;
    }

    public int getStep() {
        return this.step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public void setResponses(ArrayList<Response> responses) {
        this.responses = responses;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(question);
        parcel.writeInt(step);
    }
}
