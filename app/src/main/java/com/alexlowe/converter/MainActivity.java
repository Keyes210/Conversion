package com.alexlowe.converter;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends Activity {

    private Spinner unitTypeSpinner;
    private EditText amountEditText;

    private TextView tspTextView, tbsTextView, cupTextView, ozTextView, pintTextView, qtTextView,
                     galTextView, lbTextView, mlTextView, ltTextView, mgTextView, kgTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addItemsToUnitTypeSpinner();
        addListenerToUnitTypeSpinner();

        amountEditText = (EditText) findViewById(R.id.amount_text_view);
        initializeTextViews();
    }

    private void addListenerToUnitTypeSpinner() {
        unitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);

        unitTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelected = parent.getItemAtPosition(position).toString();

                checkIfConvertingFromTsp(itemSelected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void checkIfConvertingFromTsp(String currentUnit) {
        if(currentUnit.equals("tsp")){
            updateUnitTypeUsingTsp(Quantity.Unit.tsp);
        }else if (currentUnit.equals("tbs")){
            updateUnitTypeUsingOther(Quantity.Unit.tbs);
        }else if (currentUnit.equals("cup")){
            updateUnitTypeUsingOther(Quantity.Unit.cup);
        }else if (currentUnit.equals("oz")){
            updateUnitTypeUsingOther(Quantity.Unit.oz);
        }else if (currentUnit.equals("pint")){
            updateUnitTypeUsingOther(Quantity.Unit.pint);
        }else if (currentUnit.equals("qt")){
            updateUnitTypeUsingOther(Quantity.Unit.qt);
        }else if (currentUnit.equals("gal")){
            updateUnitTypeUsingOther(Quantity.Unit.gal);
        }else if (currentUnit.equals("lb")){
            updateUnitTypeUsingOther(Quantity.Unit.lb);
        }else if (currentUnit.equals("ml")){
            updateUnitTypeUsingOther(Quantity.Unit.ml);
        }else if (currentUnit.equals("lt")){
            updateUnitTypeUsingOther(Quantity.Unit.lt);
        }else if (currentUnit.equals("mg")){
            updateUnitTypeUsingOther(Quantity.Unit.mg);
        }else {
            updateUnitTypeUsingOther(Quantity.Unit.kg);
        }


        }

    public void updateUnitTypeUsingOther(Quantity.Unit currentUnit) {
        double doubleToConvert = Double.parseDouble(amountEditText.getText().toString());

        Quantity unitQuantity = new Quantity(doubleToConvert, currentUnit);

        String valueInTsp = unitQuantity.to(Quantity.Unit.tsp).toString();

        tspTextView.setText(valueInTsp);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.tbs, tbsTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.cup, cupTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.oz, ozTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.pint, pintTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.qt, qtTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.gal, galTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.lb, lbTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.ml, mlTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.lt, ltTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.mg, mgTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.kg, kgTextView);

        if(currentUnit.name().equals(unitQuantity.mUnit.name())){
            String currentUnitTextViewText = doubleToConvert + " " + unitQuantity.mUnit.name();
            String currentTextViewName = unitQuantity.mUnit.name() + "_text_view";

            int currentId = getResources().getIdentifier(currentTextViewName, "id", MainActivity.this.getPackageName());

            TextView currentTextView = (TextView) findViewById(currentId);
            currentTextView.setText(currentUnitTextViewText);
        }


    }

    private void updateUnitTextFieldUsingTsp(double doubleToConvert, Quantity.Unit currentUnit,
                                             Quantity.Unit preferredUnit, TextView theTextView) {

        Quantity currentQuantitySelected = new Quantity(doubleToConvert, currentUnit);

        String tempTextViewText = currentQuantitySelected.to(Quantity.Unit.tsp).to(preferredUnit).toString();

        theTextView.setText(tempTextViewText);

    }



    public void updateUnitTypeUsingTsp(Quantity.Unit currentUnit) {
        double doubleToConvert = Double.parseDouble(amountEditText.getText().toString());

        String tspValueAndUnit = doubleToConvert + " tsp";

        tspTextView.setText(tspValueAndUnit);

        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.tbs, tspTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.cup, cupTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.oz, ozTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.pint, pintTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.qt, qtTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.gal, galTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.lb, lbTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.ml, mlTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.lt, ltTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.mg, mgTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.kg, kgTextView);

    }

    public void updateUnitTextFieldUsingTsp(double doubleToConvert, Quantity.Unit unitConvertingTo,
                                            TextView unitTextView) {
        Quantity unitQuantity = new Quantity(doubleToConvert, Quantity.Unit.tsp);

        String tempUnit = unitQuantity.to(unitConvertingTo).toString();

        unitTextView.setText(tempUnit);
    }



    private void addItemsToUnitTypeSpinner() {
        unitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.conversion_types, android.R.layout.simple_spinner_item);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        unitTypeSpinner.setAdapter(spinnerAdapter);

    }

    private void initializeTextViews() {
        tspTextView = (TextView) findViewById(R.id.tsp_text_view);
        tbsTextView = (TextView) findViewById(R.id.tbs_text_view);
        cupTextView = (TextView) findViewById(R.id.cup_text_view);
        ozTextView = (TextView) findViewById(R.id.oz_text_view);
        pintTextView = (TextView) findViewById(R.id.pint_text_view);
        qtTextView = (TextView) findViewById(R.id.qt_text_view);
        galTextView = (TextView) findViewById(R.id.gal_text_view);
        lbTextView = (TextView) findViewById(R.id.lb_text_view);
        mlTextView = (TextView) findViewById(R.id.ml_text_view);
        ltTextView = (TextView) findViewById(R.id.lt_text_view);
        mgTextView = (TextView) findViewById(R.id.mg_text_view);
        kgTextView = (TextView) findViewById(R.id.kg_text_view);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
