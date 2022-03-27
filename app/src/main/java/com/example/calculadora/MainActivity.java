package com.example.calculadora;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calculadora.databinding.ActivityMainBinding;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding mainBinding;
    private String operation = "";
    private float bigDecimal;
    private Integer bigInteger;
    private String symbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mainBinding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(this.mainBinding.getRoot());
        startUI();
    }

    private void startUI() {
        this.mainBinding.buttonOne.setOnClickListener(this);
        this.mainBinding.buttonTwo.setOnClickListener(this);
        this.mainBinding.buttonThree.setOnClickListener(this);
        this.mainBinding.buttonFour.setOnClickListener(this);
        this.mainBinding.buttonFive.setOnClickListener(this);
        this.mainBinding.buttonSix.setOnClickListener(this);
        this.mainBinding.buttonSeven.setOnClickListener(this);
        this.mainBinding.buttonEight.setOnClickListener(this);
        this.mainBinding.buttonNine.setOnClickListener(this);
        this.mainBinding.buttonZero.setOnClickListener(this);
        this.mainBinding.buttonPoint.setOnClickListener(this);
        this.mainBinding.buttonCleanAll.setOnClickListener(this);
        this.mainBinding.buttonCLean.setOnClickListener(this);
        this.mainBinding.buttonDivide.setOnClickListener(this);
        this.mainBinding.buttonMultiply.setOnClickListener(this);
        this.mainBinding.buttonSubTract.setOnClickListener(this);
        this.mainBinding.buttonPlus.setOnClickListener(this);
        this.mainBinding.buttonEqual.setOnClickListener(this);
    }

    private void setTextOperation(int digit) {
        if (this.operation.equals("0")) {
            this.operation = String.format("%d", digit);
        } else {
            this.operation = String.format("%s%d", this.operation, digit);
        }
    }

    private void removeCaracter() {
        if (!this.operation.isEmpty()) {
            this.operation = this.operation.substring(0, this.operation.length() - 1);
        }
    }

    private void setPoint() {
        String isPoint = ",";
        int isContaintPoint = this.operation.indexOf(isPoint);
        if (!this.operation.isEmpty() && isContaintPoint < 0) {
            this.operation = String.format("%s%s", this.operation, isPoint);
        }
    }

    private boolean lastIsSymbol() {
        String original = "-+x/";
        boolean isContaintSymbol = false;

        for (int i = 0; i < original.length(); i++) {
            if (this.operation.charAt(this.operation.length()) == original.charAt(i)) {
                isContaintSymbol = true;
            }
        }
        return isContaintSymbol;
    }

    private void setDivide() {
        symbol = "/";
        int isContaintSymbol = this.operation.indexOf(symbol);
        if (!this.operation.isEmpty() && isContaintSymbol < 0) {
            processResult();
            this.operation = String.format("%s%s", this.operation, symbol);
        }
    }

    private void setSubtract() {
        symbol = "-";
        int isContaintSubtract = this.operation.indexOf(symbol);
        if (!this.operation.isEmpty() && isContaintSubtract < 0) {
            processResult();
            this.operation = String.format("%s%s", this.operation, symbol);
        }
    }

    private void setMultiply() {
        symbol = "x";
        int isContaintMultiply = this.operation.indexOf(symbol);
        if (!this.operation.isEmpty() && isContaintMultiply < 0) {
            processResult();
            this.operation = String.format("%s%s", this.operation, symbol);
        }
    }

    private void setPlus() {
        symbol = "+";
        int isContaintPlus = this.operation.indexOf(symbol);
        if (!this.operation.isEmpty() && isContaintPlus < 0) {
            processResult();
            this.operation = String.format("%s%s", this.operation, symbol);
        }
    }

    private void processResult() {
        String symbolpoint = ",";
        int isContaintPoint = this.operation.indexOf(symbolpoint);
        int isContaintSymbol = this.operation.indexOf(symbol);

        String part1 = null;
        String part2 = null;

        if (isContaintPoint >= 0 && isContaintSymbol > 0) {
            part1 = this.operation.substring(0, isContaintSymbol);
            part2 = this.operation.substring(isContaintSymbol + 1, this.operation.length());
            part1 = part1.replaceAll(",", ".");
            part2 = part2.replaceAll(",", ".");
        } else if (isContaintSymbol > 0) {
            part1 = this.operation.substring(0, isContaintSymbol);
            part2 = this.operation.substring(isContaintSymbol + 1, this.operation.length());
        }

        if (isContaintSymbol > 0 && symbol.equals("/")) {
            if (isContaintPoint >= 0) {
                bigDecimal = Float.parseFloat(part1) / Float.parseFloat(part2);
            } else {
                if (Integer.parseInt(part1) < Integer.parseInt(part2)) {
                    bigDecimal = Float.parseFloat(part1) / Float.parseFloat(part2);
                    isContaintPoint = 0;
                    isContaintSymbol = 1;
                } else
                    bigInteger = Integer.parseInt(part1) / Integer.parseInt(part2);
            }
        } else if (isContaintSymbol > 0 && symbol.equals("-")) {
            if (isContaintPoint >= 0) {
                bigDecimal = Float.parseFloat(part1) - Float.parseFloat(part2);
            } else {
                bigInteger = Integer.parseInt(part1) - Integer.parseInt(part2);
            }
        } else if (isContaintSymbol > 0 && symbol.equals("x")) {
            if (isContaintPoint >= 0) {
                bigDecimal = Float.parseFloat(part1) * Float.parseFloat(part2);
            } else {
                bigInteger = Integer.parseInt(part1) * Integer.parseInt(part2);
            }
        } else if (isContaintSymbol > 0 && symbol.equals("+")) {
            if (isContaintPoint >= 0) {
                bigDecimal = Float.parseFloat(part1) + Float.parseFloat(part2);
            } else {
                bigInteger = Integer.parseInt(part1) + Integer.parseInt(part2);
            }
        }

        if (isContaintPoint >= 0 && isContaintSymbol > 0) {
            this.operation = String.valueOf(bigDecimal);
            this.operation = this.operation.replace(".", ",");
        } else if (isContaintSymbol > 0) {
            this.operation = String.valueOf(bigInteger);
        }
    }

    @Override
    public void onClick(View view) {
        if (this.mainBinding.buttonOne == view) {
            setTextOperation(1);
        } else if (this.mainBinding.buttonTwo == view) {
            setTextOperation(2);
        } else if (this.mainBinding.buttonThree == view) {
            setTextOperation(3);
        } else if (this.mainBinding.buttonFour == view) {
            setTextOperation(4);
        } else if (this.mainBinding.buttonFive == view) {
            setTextOperation(5);
        } else if (this.mainBinding.buttonSix == view) {
            setTextOperation(6);
        } else if (this.mainBinding.buttonSeven == view) {
            setTextOperation(7);
        } else if (this.mainBinding.buttonEight == view) {
            setTextOperation(8);
        } else if (this.mainBinding.buttonNine == view) {
            setTextOperation(9);
        } else if (this.mainBinding.buttonZero == view) {
            setTextOperation(0);
        } else if (this.mainBinding.buttonPoint == view) {
            setPoint();
        } else if (this.mainBinding.buttonCleanAll == view) {
            this.operation = "";
        } else if (this.mainBinding.buttonCLean == view) {
            removeCaracter();
        } else if (this.mainBinding.buttonDivide == view) {
            setDivide();
        } else if (this.mainBinding.buttonMultiply == view) {
            setMultiply();
        } else if (this.mainBinding.buttonSubTract == view) {
            setSubtract();
        } else if (this.mainBinding.buttonPlus == view) {
            setPlus();
        } else if (this.mainBinding.buttonEqual == view) {
            processResult();
        }

        this.mainBinding.operation.setText(this.operation.trim());
    }
}