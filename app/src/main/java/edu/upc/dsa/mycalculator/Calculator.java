package edu.upc.dsa.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class Calculator extends AppCompatActivity {

    TextView result;

    List<String> operation;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        this.operation = new LinkedList<>();
        
        this.result = findViewById(R.id.resultText);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.zeroBtn:
                break;
            case R.id.oneBtn:
                break;
            case R.id.twoBtn:
                break;
            case R.id.threeBtn:
                break;
            case R.id.fourBtn:
                break;
            case R.id.fiveBtn:
                break;
            case R.id.sixBtn:
                break;
            case R.id.sevenBtn:
                break;
            case R.id.eightBtn:
                break;
            case R.id.nineBtn:
                break;
            case R.id.sumBtn:
                break;
            case R.id.divideBtn:
                break;
            case R.id.comaBtn:
                break;
            case R.id.equalsBtn:
                break;
        }

        }
    }
}