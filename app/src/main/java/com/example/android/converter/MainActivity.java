package com.example.android.converter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;


public class MainActivity extends ActionBarActivity {

    // Quantities representing 1 tsp (teaspoon)
    private static final double tsp= 1.0d;
    private static final double tbsp= 0.3333d;
    private static final double cup=0.0208d;
    private static final double oz=0.1666d;
    private static final double pint=0.0104d;
    private static final double quart=0.0052d;
    private static final double gallon=0.0013d;
    private static final double pound=0.0125d;
    private static final double ml=4.9289d;
    private static final double liter=0.0049d;
    private static final double mg=5687.5d;
    private static final double kg=0.0057d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
        //
        Spinner spinner = (Spinner) findViewById(R.id.unit_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.units_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //actions

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String unitFrom = adapterView.getItemAtPosition(i ).toString();
                EditText inputValue = (EditText) findViewById(R.id.input_edit_text);
                Double val = new Double(inputValue.getText().toString());
                performCalculations(unitFrom,val);
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // EditText "Done" event will perform calculations as well
        final EditText inputEditText = (EditText) findViewById(R.id.input_edit_text);
        inputEditText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    Spinner spinner = (Spinner) findViewById(R.id.unit_spinner);
                    String unitFrom = spinner.getSelectedItem().toString();
                    EditText inputValue = (EditText) findViewById(R.id.input_edit_text);
                    Double val = new Double(inputValue.getText().toString());
                    performCalculations(unitFrom,val );
                    return true;
                }
                return false;
            }
        });
    }

      /**
     * It checks if its not teaspoon then convert the source value
     * to teaspoon and forward to the method that updates all TextViews
     * @param unitFrom
     * @param val
     */
    private void performCalculations(String unitFrom,double val)
    {

        if(!unitFrom.equals("tsp"))
        {
            if(unitFrom.equals("kg"))
            {
                val=val*(1/kg);
            }else if(unitFrom.equals("tbsp"))
            {
                val=val*(1/tbsp);
            }
            else if(unitFrom.equals("pound"))
            {
                val=val*(1/pound);
            }
            else if(unitFrom.equals("ml"))
            {
                val=val*(1/ml);
            }
            else if(unitFrom.equals("liter"))
            {
                val=val*(1/liter);
            }
            else if(unitFrom.equals("cup"))
            {
                val=val*(1/cup);
            }
            else if(unitFrom.equals("oz"))
            {
                val=val*(1/oz);
            }
            else if(unitFrom.equals("pint"))
            {
                val=val*(1/pint);
            }else if(unitFrom.equals("gallon"))
            {
                val=val*(1/gallon);
            }
            else if(unitFrom.equals("pound"))
            {
                val=val*(1/pound);
            }
            else if(unitFrom.equals("mg"))
            {
                val=val*(1/mg);
            }
        }
        // Converts all measures
        convertFromTeaspoonToAll(val);
    }

    /**
     *  Converts a value from teaspoon to all types
     * @param val value to be converted
     */
    private void convertFromTeaspoonToAll(double val) {
        double converted;

        TextView textViewVal;

        textViewVal = (TextView) findViewById(R.id.kg_val_text_view);
        converted = val*kg;
        trimToDecimals(textViewVal,converted);

        textViewVal = (TextView) findViewById(R.id.tsp_val_text_view);
        converted = val*tsp;
        trimToDecimals(textViewVal,converted);

        textViewVal= (TextView) findViewById(R.id.tbsp_val_text_view);
        converted = val*tbsp;
        trimToDecimals(textViewVal,converted);

        textViewVal = (TextView) findViewById(R.id.pound_val_text_view);
        converted = val*pound;
        trimToDecimals(textViewVal,converted);

        textViewVal = (TextView) findViewById(R.id.gallon_val_text_view);
        converted = val*gallon;
        trimToDecimals(textViewVal,converted);

        textViewVal = (TextView) findViewById(R.id.quart_val_text_view);
        converted = val*quart;
        trimToDecimals(textViewVal,converted);

        textViewVal = (TextView) findViewById(R.id.mg_val_text_view);
        converted = val*mg;
        trimToDecimals(textViewVal,converted);

        textViewVal = (TextView) findViewById(R.id.liter_val_text_view);
        converted = val*liter;
        trimToDecimals(textViewVal,converted);

        textViewVal = (TextView) findViewById(R.id.cup_val_text_view);
        converted = val*cup;
        trimToDecimals(textViewVal,converted);

        textViewVal = (TextView) findViewById(R.id.oz_val_text_view);
        converted = val*oz;
        trimToDecimals(textViewVal,converted);

        textViewVal = (TextView) findViewById(R.id.pint_val_text_view);
        converted = val*pint;
        trimToDecimals(textViewVal,converted);

        textViewVal = (TextView) findViewById(R.id.ml_val_text_view);
        converted = val*ml;
        trimToDecimals(textViewVal,converted);
    }

    /**
     * Formats the value accordingly and updates the TextView
     * @param tv
     * @param value
     */
    private void trimToDecimals(TextView tv, double value)
    {
        String pattern = "##.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String finalValue = decimalFormat.format(value);
        tv.setText(finalValue);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
