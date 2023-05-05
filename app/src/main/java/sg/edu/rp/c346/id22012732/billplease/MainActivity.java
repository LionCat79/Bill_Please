package sg.edu.rp.c346.id22012732.billplease;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText amountEditText;
    EditText paxEditText;
    ToggleButton svsToggleButton;
    ToggleButton gstToggleButton;
    EditText discountEditText;
    RadioGroup paymentRadioGroup;
    RadioButton cashRadioButton;
    RadioButton paypalRadioButton;
    TextView totalBillTextView;
    TextView eachPaysTextView;
    Button splitButton;
    Button resetButton;

    double amount;
    int pax;
    boolean hasSvs;
    boolean hasGst;
    double discount;
    boolean isCashPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountEditText = findViewById(R.id.enterAmount);
        paxEditText = findViewById(R.id.enterPax);
        svsToggleButton = findViewById(R.id.svs);
        gstToggleButton = findViewById(R.id.gst);
        discountEditText = findViewById(R.id.enterDiscount);
        paymentRadioGroup = findViewById(R.id.paymentMethod);
        cashRadioButton = findViewById(R.id.radioButtonCash);
        paypalRadioButton = findViewById(R.id.radioButtonPaypal);
        totalBillTextView = findViewById(R.id.totalBillDisplay);
        eachPaysTextView = findViewById(R.id.eachPaysDisplay);
        splitButton = findViewById(R.id.buttonSplit);
        resetButton = findViewById(R.id.buttonReset);

        splitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount = Double.parseDouble(amountEditText.getText().toString());
                pax = Integer.parseInt(paxEditText.getText().toString());
                hasSvs = svsToggleButton.isChecked();
                hasGst = gstToggleButton.isChecked();
                discount = Double.parseDouble(discountEditText.getText().toString());
                isCashPayment = cashRadioButton.isChecked();

                double totalBill = amount;
                if (hasSvs) {
                    totalBill *= 1.10;
                }
                if (hasGst) {
                    totalBill *= 1.07;
                }
                totalBill *= (1 - discount / 100);

                totalBillTextView.setText("Total bill: " + String.format("%.2f", totalBill));

                double eachPays = totalBill / pax;

                if (isCashPayment) {
                    eachPaysTextView.setText("Each pays: " + String.format("%.2f", eachPays) + " in cash");
                } else {
                    eachPaysTextView.setText("Each pays: " + String.format("%.2f", eachPays) + " via Paypal");
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountEditText.setText("");
                paxEditText.setText("");
                svsToggleButton.setChecked(false);
                gstToggleButton.setChecked(false);
                discountEditText.setText("");
                paymentRadioGroup.clearCheck();
                totalBillTextView.setText("Total bill: ");
                eachPaysTextView.setText("Each pays: ");
            }
        });
    }
}