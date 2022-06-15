package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class calculator extends AppCompatActivity {
    TextView inputText,outputText;
    String input,output;
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,Division_btn,Multiply_btn,btn_,btn_clear,Minus_btn,Plus_btn,equal_btn,Power_btn,Percent_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        btn0 = findViewById(R.id.btn_0);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        btn6 = findViewById(R.id.btn_6);
        btn7 = findViewById(R.id.btn_7);
        btn8 = findViewById(R.id.btn_8);
        btn9 = findViewById(R.id.btn_9);
        Division_btn = findViewById(R.id.div_btn);
        Multiply_btn = findViewById(R.id.multiply_btn);
        Minus_btn = findViewById(R.id.minus_btn);
        Plus_btn = findViewById(R.id.plus_btn);
        Power_btn = findViewById(R.id.power_btn);
        btn_ = findViewById(R.id.btn_);
        btn_clear = findViewById(R.id.clearbutton);
        equal_btn = findViewById(R.id.equal_btn);
        Percent_btn = findViewById(R.id.per_btn);

    }
    public void onButtonClicked(View view){
        Button button = (Button) view;
        String data = button.getText().toString();
        switch (data){

            case "C":
                input=null;
                output=null;
                outputText.setText("");
                break;
            case "^":
                input+="^";
                solve();
                break;
            case "*":
                input+="*";
                solve();
                break;
            case "=":
                solve();
                break;
            case "%":
                input="%";
                double d= Double.parseDouble(inputText.getText().toString())/100;
                outputText.setText(String.valueOf(d));
                break;
            default:
                if(input==null){
                    input = "";
                }
                if(data.equals("+")||data.equals("/")||data.equals("-")){
                    solve();
                }
                input +=data;

        }
        inputText.setText(input);
    }
    private void solve(){
        Toast.makeText(com.example.assignment1.calculator.this, "Enter your Username", Toast.LENGTH_SHORT).show();
    }
}