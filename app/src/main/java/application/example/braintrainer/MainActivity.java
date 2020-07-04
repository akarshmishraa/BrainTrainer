package application.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int c=0; int w=0,l=0;
    int optionSelect; ArrayList<Integer> option;
    TextView question;int no2; int no1;
    Button optionButton4;
    Button optionButton3;
    Button optionButton2;
    Button optionButton1;
    TextView score;
    public int rndmno()
    {
        int min=9;
        int max=99;
        int random=new Random().nextInt(max-min+1)+min;
        return(random);
    }
    public int rndmoption()
    {
        int min=1;
        int max=4;
        int random=new Random().nextInt(max-min+1)+min;
        return(random);
    }
    public void  clickOption(View view) {
        score=(TextView)findViewById(R.id.score);
        l++;
        Button option = (Button) view;
        if (Integer.parseInt(option.getTag().toString()) == optionSelect) {
            question.setText("CORRECT!");
            w++;
        }
        else {
            question.setText("WRONG!");
        }
        score.setText(Integer.toString(w)+"/"+Integer.toString(l));
        viewChange();

    }
    public void extra()
    {
        optionSelect=rndmoption();
        for(int i=1;i<=4;i++)
        {
            if(optionSelect==i)
            {
                option.add(no1+no2);
            }
            else
                option.add(rndmno()+rndmno());

        }
        optionButton1.setText(Integer.toString(option.get(0)));
        optionButton2.setText(Integer.toString(option.get(1)));
        optionButton3.setText(Integer.toString(option.get(2)));
        optionButton4.setText(Integer.toString(option.get(3)));
        option.clear();
    }
    public void viewChange()
    {
        question=(TextView) findViewById(R.id.question);
        no1=rndmno();
        no2=rndmno();
        option=new ArrayList<Integer>();

        question.setText(Integer.toString(no1) +"+"+Integer.toString(no2));
        optionButton1=(Button)findViewById(R.id.optionButton1);
        optionButton2=(Button)findViewById(R.id.optionButton2);
        optionButton3=(Button)findViewById(R.id.optionButton3);
        optionButton4=(Button)findViewById(R.id.optionButton4);


        extra();
    }
    public void click(View view)
    {

        final Button button=(Button) findViewById(R.id.startButton);
        button.setEnabled(false);
        final TextView timer=(TextView) findViewById(R.id.timer);
        final MediaPlayer media=MediaPlayer.create(this,R.raw.airhorn);
        viewChange();
        TextView score1=(TextView)findViewById(R.id.score);
        score1.setText("--");

        CountDownTimer count=new CountDownTimer(45000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if((int)millisUntilFinished/1000>9)
                timer.setText("00:"+(int)millisUntilFinished/1000);
                else
                    timer.setText("00:0"+(int)millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {
                media.start();

            question.setText("Ready?");

            optionButton1.setEnabled(false);
                optionButton2.setEnabled(false);
                optionButton3.setEnabled(false);
                optionButton4.setEnabled(false);
                button.setEnabled(true);
            }
        }.start();
        optionButton4.setEnabled(true);
        optionButton1.setEnabled(true);
        optionButton2.setEnabled(true);
        optionButton3.setEnabled(true);
        w=0;l=0;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
