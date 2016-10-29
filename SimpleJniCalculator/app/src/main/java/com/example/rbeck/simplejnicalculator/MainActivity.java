package com.example.rbeck.simplejnicalculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


    public class MainActivity extends Activity implements View.OnClickListener {

        EditText etNum1;
        EditText etNum2;

        Button btnAdd;
        Button btnSub;
        Button btnMult;
        Button btnDiv;

        TextView tvResult;

        String oper = "";



        /**
         * A native method that is implemented by the 'native-lib' native library,
         * which is packaged with this application.
         */

        public native float jniCalc(float[] jA);


        // Used to load the 'native-lib' library on application startup.
        static {
            System.loadLibrary("native-lib");
        }

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);

            // find the elements
            etNum1 = (EditText) findViewById(R.id.etNum1);
            etNum2 = (EditText) findViewById(R.id.etNum2);

            btnAdd = (Button) findViewById(R.id.btnAdd);
            btnSub = (Button) findViewById(R.id.btnSub);
            btnMult = (Button) findViewById(R.id.btnMult);
            btnDiv = (Button) findViewById(R.id.btnDiv);

            tvResult = (TextView) findViewById(R.id.tvResult);

            // set a listener
            btnAdd.setOnClickListener(this);
            btnSub.setOnClickListener(this);
            btnMult.setOnClickListener(this);
            btnDiv.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub


            final float ADD  =1;
            final float SUB  =2;
            final float MULT =3;
            final float DIV  =4;

            float [] fA = {0f, 0f, 0f};

            float num1 = 0;
            float num2 = 0;
            float result = 0;

            // check if the fields are empty
            if (TextUtils.isEmpty(etNum1.getText().toString())
                    || TextUtils.isEmpty(etNum2.getText().toString())) {
                return;
            }

            // read EditText and fill variables with numbers
            num1 = Float.parseFloat(etNum1.getText().toString());
            num2 = Float.parseFloat(etNum2.getText().toString());

            // defines the button that has been clicked and performs the corresponding operation
            // write operation into oper, we will use it later for output
            switch (v.getId()) {
                case R.id.btnAdd:
                    oper = "+";
                    //result = num1 + num2;
                    fA[0] = ADD;
                    break;

                case R.id.btnSub:
                    oper = "-";
                    //result = num1 - num2;
                    fA[0] = SUB;
                    break;

                case R.id.btnMult:
                    oper = "*";
                    //result = num1 * num2;
                    fA[0] = MULT;
                    break;

                case R.id.btnDiv:
                    oper = "/";
                    //result = num1 / num2;
                    fA[0] = DIV;
                    break;

                default:
                    this.tvResult.setText("Internal Error");
                    break;
            }

            fA[1] = num1;
            fA[2] = num2;
            result = jniCalc(fA);

            // form the output line
            this.tvResult.setText(num1 + " " + this.oper + " " + num2 + " = " + result);
        }
    }

