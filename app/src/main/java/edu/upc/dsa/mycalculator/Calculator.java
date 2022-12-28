package edu.upc.dsa.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Calculator extends AppCompatActivity {

    TextView input;
    TextView result;

    Double numericResult;

    LinkedList<String> operation;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        this.operation = new LinkedList<>();

        this.input = findViewById(R.id.inputText);
        this.result = findViewById(R.id.resultText);
    }

    @SuppressLint("SetTextI18n")
    public void addOperationElement(View view) {
       Button buttonClicked = (Button) view;
       String text = buttonClicked.getText().toString();
       operation.add(text);
       input.setText(input.getText() + text);
    }

    public void deleteOperationElement(View view) {
        StringBuffer oldText = new StringBuffer(input.getText().toString());
        operation.removeLast();
        input.setText(oldText.deleteCharAt(oldText.length()-1));
    }

    public void performOperation(View view) {
        LinkedList<String> operationCorrected = new LinkedList<>();
        StringBuffer wholeNumber = new StringBuffer();

        for (String value : this.operation){
            if (isNumber(value)){
                wholeNumber.append(value);
                return;
            }
            if (!wholeNumber.toString().equals(""))
                operationCorrected.add(wholeNumber.toString());
            operationCorrected.add(value);
            wholeNumber = new StringBuffer();
        }
        this.operation = operationCorrected;
        performDivisionMultiplication();

    }

    private void performDivisionMultiplication() {
        LinkedList<String> operationCorrected = new LinkedList<>();
        while (true) {
            int index = firstDivisionOrMultiplication();
            if(index == -1){
                return;
            }
            if (Objects.equals(this.operation.get(index), "/")){
                Double result = Double.parseDouble(this.operation.get(index-1))/Double.parseDouble(this.operation.get(index+1));
                operationCorrected.add(result.toString());
            }


        }
    }

    private boolean isNumber(String value){
        if (!Objects.equals(value, "/") && !Objects.equals(value, "-") &&
                !Objects.equals(value, "+") && !Objects.equals(value, "x"))
            return true;
        return false;
    }

    private int firstDivisionOrMultiplication() {
        for(int i = 0; i < this.operation.size(); i++){
            if (Objects.equals(this.operation.get(i), "/") || Objects.equals(this.operation.get(i), "x")){
                return i;
            }
        }
        return -1;
    }

}