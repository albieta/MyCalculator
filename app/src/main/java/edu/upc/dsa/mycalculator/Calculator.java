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
    Button typeButton;
    String type;

    LinkedList<String> operation;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        this.operation = new LinkedList<>();

        this.input = findViewById(R.id.inputText);
        this.result = findViewById(R.id.resultText);
        this.typeButton = findViewById(R.id.typeBtn);
        this.type = "rad";
    }

    public void changeType(View view) {
        if(Objects.equals(this.type, "rad")){
            this.type = "deg";
            this.typeButton.setText("deg");
        } else {
            this.type = "rad";
            this.typeButton.setText("rad");
        }
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
            } else {
                if (!wholeNumber.toString().equals(""))
                    operationCorrected.add(wholeNumber.toString());
                operationCorrected.add(value);
                wholeNumber = new StringBuffer();
            }
        }
        operationCorrected.add(wholeNumber.toString());
        this.operation = operationCorrected;
        performSinCosTan();
        performDivisionMultiplication();
        performAdditionSubtraction();

        this.result.setText(this.operation.get(0));
        this.input.setText("");
        this.operation = new LinkedList<>();
    }

    private void performSinCosTan() {
        while (true) {
            int index = firstSinCosTan();
            if(index == -1){
                break;
            }
            Double result;
            if (Objects.equals(this.operation.get(index), "sin:")) {
                if (Objects.equals(type, "deg"))
                    result = Math.sin(Math.toRadians(Double.parseDouble(this.operation.get(index+1))));
                else
                    result = Math.sin(Double.parseDouble(this.operation.get(index+1)));
                this.operation.add(index, result.toString());
            }
            if (Objects.equals(this.operation.get(index), "cos:")) {
                if (Objects.equals(type, "deg"))
                    result = Math.cos(Math.toRadians(Double.parseDouble(this.operation.get(index+1))));
                else
                    result = Math.cos(Double.parseDouble(this.operation.get(index+1)));
                this.operation.add(index, result.toString());
            }
            if (Objects.equals(this.operation.get(index), "tan:")) {
                if (Objects.equals(type, "deg"))
                    result = Math.tan(Math.toRadians(Double.parseDouble(this.operation.get(index+1))));
                else
                    result = Math.tan(Double.parseDouble(this.operation.get(index+1)));
                this.operation.add(index, result.toString());
            }
            this.operation.remove(index+1);
            this.operation.remove(index+1);
        }
    }

    private void performDivisionMultiplication() {
        while (true) {
            int index = firstDivisionOrMultiplication();
            if(index == -1){
                break;
            }
            if (Objects.equals(this.operation.get(index), "/")){
                Double result = Double.parseDouble(this.operation.get(index-1))/Double.parseDouble(this.operation.get(index+1));
                this.operation.add(index-1, result.toString());
            } else {
                Double result = Double.parseDouble(this.operation.get(index-1))*Double.parseDouble(this.operation.get(index+1));
                this.operation.add(index-1, result.toString());
            }
            refreshOperation(index);
        }
    }

    private void performAdditionSubtraction() {
        while (true) {
            int index = firstAdditionSubtraction();
            if(index == -1){
                break;
            }
            if (Objects.equals(this.operation.get(index), "+")){
                Double result = Double.parseDouble(this.operation.get(index-1))+Double.parseDouble(this.operation.get(index+1));
                this.operation.add(index-1, result.toString());
            } else {
                Double result = Double.parseDouble(this.operation.get(index-1))-Double.parseDouble(this.operation.get(index+1));
                this.operation.add(index-1, result.toString());
            }
            refreshOperation(index);
        }
    }

    private boolean isNumber(String value){
        if (!Objects.equals(value, "/") && !Objects.equals(value, "-") &&
                !Objects.equals(value, "+") && !Objects.equals(value, "x") &&
                !Objects.equals(value, "sin:") && !Objects.equals(value, "cos:") && !Objects.equals(value, "tan:"))
            return true;
        return false;
    }

    private int firstSinCosTan() {
        for(int i = 0; i < this.operation.size(); i++){
            if (Objects.equals(this.operation.get(i), "sin:") || Objects.equals(this.operation.get(i), "cos:")
                    || Objects.equals(this.operation.get(i), "tan:")){
                return i;
            }
        }
        return -1;
    }

    private int firstDivisionOrMultiplication() {
        for(int i = 0; i < this.operation.size(); i++){
            if (Objects.equals(this.operation.get(i), "/") || Objects.equals(this.operation.get(i), "x")){
                return i;
            }
        }
        return -1;
    }

    private int firstAdditionSubtraction() {
        for(int i = 0; i < this.operation.size(); i++){
            if (Objects.equals(this.operation.get(i), "+") || Objects.equals(this.operation.get(i), "-")){
                return i;
            }
        }
        return -1;
    }

    private void refreshOperation(int index) {
        this.operation.remove(index);
        this.operation.remove(index);
        this.operation.remove(index);
    }
}