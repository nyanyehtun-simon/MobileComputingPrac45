package com.example.mobilecomputingprac4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingFragment extends Fragment  implements AdapterView.OnItemSelectedListener {

    Spinner celebrityNumSpinner;
    OnSpinnerValueChangedListener callback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        celebrityNumSpinner = (Spinner) getView().findViewById(R.id.celebrity_num_spinner);

        ArrayAdapter spinnerArrayAdapter = (ArrayAdapter) celebrityNumSpinner.getAdapter();

        int spinnerPos = spinnerArrayAdapter.getPosition("3");

        celebrityNumSpinner.setSelection(spinnerPos);

        celebrityNumSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        System.out.println(celebrityNumSpinner.getSelectedItem());
        callback.onSpinnerValueChanged(Integer.parseInt(celebrityNumSpinner.getSelectedItem().toString()));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setOnSpinnerValueChangedListener(OnSpinnerValueChangedListener callback) {
        this.callback = callback;
    }

    public interface OnSpinnerValueChangedListener {
        public void onSpinnerValueChanged(int value);
    }
}
