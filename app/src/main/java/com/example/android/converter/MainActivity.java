package com.example.android.converter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                Log.d("spinnerDbg",adapterView.getItemAtPosition(i ).toString());

                String unitFrom = adapterView.getItemAtPosition(i ).toString();

                EditText valEdit = (EditText) findViewById(R.id.val_edit_text);

                Double val = new Double(valEdit.getText().toString());

                updateConversions(unitFrom,val);

            //    incrementKg();



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void updateConversions(String unitFrom,double val)
    {
        // se nao for grama, converte o valor de origem pra
        // grama e repassa ao metodo que atualiza tudo
        if(!unitFrom.equals("g"))
        {
            if(unitFrom.equals("kg"))
            {
                val=val*1000;
            }else if(unitFrom.equals("tsp"))
            {
                val=val*4.739336492890995;
            }
            else if(unitFrom.equals("tbsp"))
            {
                val=val*1.501501501501502;
            }

        }
        // percorrer as 4 medidas e converter
        convertFromGramasToAll(val);
    }

    /**
     *
     * @param val valor a ser convertido
     */
    private void convertFromGramasToAll(double val) {
        double convertido;
        // assumir q unidade de origem é grama
        TextView textViewVal;

            textViewVal = (TextView) findViewById(R.id.kg_val_text_view);
            convertido = val*0.001;
            textViewVal.setText(convertido+ "");


            textViewVal = (TextView) findViewById(R.id.tsp_val_text_view);
            convertido = val*0.211;
            textViewVal.setText(convertido+ "");


            textViewVal= (TextView) findViewById(R.id.tbsp_val_text_view);
            convertido = val*0.666;
            textViewVal.setText(convertido+ "");

            textViewVal = (TextView) findViewById(R.id.g_val_text_view);
            convertido = val*1;
            textViewVal.setText(convertido+ "");
    }

    private void incrementKg() {
        TextView kg = (TextView) findViewById(R.id.kg_text_view);
        String val = kg.getText().toString();
        Log.d("fcc",kg.toString());
        Integer inc = new Integer(val);
        inc++;
        Log.d("fcc: inteiro",""+inc);

        kg.setText(inc+"");
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
