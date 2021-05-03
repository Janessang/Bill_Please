//Janessa 20025312
package sg.edu.rp.c346.id20025312.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText amtDisplay;
    EditText numDisplay;
    EditText disDisplay;
    ToggleButton svsDisplay;
    ToggleButton gstDisplay;
    Button splitDisplay;
    Button resetDisplay;
    TextView totalDisplay;
    TextView eachDisplay;
    RadioGroup rgMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amtDisplay = findViewById(R.id.amountDisplay);
        numDisplay = findViewById(R.id.num_of_paxDisplay);
        disDisplay = findViewById(R.id.discountDisplay);
        svsDisplay = findViewById(R.id.svsDisplay);
        gstDisplay = findViewById(R.id.gstDisplay);
        splitDisplay = findViewById(R.id.split);
        resetDisplay = findViewById(R.id.reset);
        totalDisplay = findViewById(R.id.totalDisplay);
        eachDisplay = findViewById(R.id.eachDisplay);
        rgMethod = findViewById(R.id.radiogroupmethod);

        splitDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amtDisplay.getText().toString().trim().length() != 0 && numDisplay.getText().toString().trim().length() != 0) {
                    double newAmt = 0;

                    if (svsDisplay.isChecked() && gstDisplay.isChecked()) {
                        newAmt = Double.parseDouble(amtDisplay.getText().toString()) * 1.17;
                    } else if (svsDisplay.isChecked() && !gstDisplay.isChecked()) {
                        newAmt = Double.parseDouble(amtDisplay.getText().toString()) * 1.10;
                    } else if (!svsDisplay.isChecked() && gstDisplay.isChecked()) {
                        newAmt = Double.parseDouble(amtDisplay.getText().toString()) * 1.07;
                    } else {
                        double ori = Double.parseDouble(amtDisplay.getText().toString());
                    }

                    if (disDisplay.getText().toString().trim().length() != 0) {
                        double discountedamt = newAmt * (Double.parseDouble(disDisplay.getText().toString()) / 100);
                        newAmt = newAmt - discountedamt;
                    }

                    int radiobuttonid = rgMethod.getCheckedRadioButtonId();
                    if (radiobuttonid == R.id.cashDisplay) {
                        totalDisplay.setText(String.format("Total Bills: $%.2f", newAmt));
                        eachDisplay.setText(String.format("Each pays: $%.2f in cash", (newAmt / Integer.parseInt(numDisplay.getText().toString()))));

                    } else {
                        totalDisplay.setText(String.format("Total Bills: $%.2f", newAmt));
                        eachDisplay.setText(String.format("Each pays: $%.2f via PayNow to 912345678", (newAmt / Integer.parseInt(numDisplay.getText().toString()))));
                    }

                }
            }
        });

        resetDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amtDisplay.setText("");
                numDisplay.setText("");
                disDisplay.setText("");
                svsDisplay.setChecked(false);
                gstDisplay.setChecked(false);
                totalDisplay.setText("");
                eachDisplay.setText("");
                rgMethod.check(R.id.cashDisplay);
            }
        });

    }
}