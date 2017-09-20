package com.mrmarapps.helloinnocv.fragmentdetailuser;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mrmarapps.helloinnocv.R;
import com.mrmarapps.helloinnocv.fragmentdetailuser.viewmodel.UserDetail;
import com.mrmarapps.helloinnocv.mvp.ViewActions;
import com.mrmarapps.helloinnocv.mvp.fragment.BaseMVPFragmentView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by mario on 14/09/17.
 */

public class FragmentDetailUserView extends BaseMVPFragmentView<FragmentDetailUser,FragmentDetailUserView.Actions> {

    private Calendar myCalendar = Calendar.getInstance();

    @BindView(R.id.input_birth_date)
    public EditText birthDate;
    @BindView(R.id.input_name)
    public EditText name;
    @BindView(R.id.tv_select_element)
    public TextView tvSelectElement;
    @BindView(R.id.container_fields)
    public LinearLayout containerFields;
    @BindView(R.id.btn_save)
    public Button btnSave;

    @Inject
    public FragmentDetailUserView(FragmentDetailUser fragment) {
        super(fragment);
    }

    @Override
    public void initView() {
        birthDate.setKeyListener(null);

    }

    public void printUserDetail(UserDetail userDetail) {
        name.setText(userDetail.getName());
        birthDate.setText(userDetail.getBirthDate());
    }



    public void showDetailView() {
        hideTextSelectElements();
        showFields();
        showSaveButton();
    }


    final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setDateIntoEditext();
        }

    };



    @OnClick(R.id.input_birth_date)
    public void onDatePick(){
        new DatePickerDialog(fragment.getContext(), date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @OnClick(R.id.btn_save)
    public void onSaved(){
        actions.onSaved(birthDate.getText().toString(),name.getText().toString());
    }

    private void setDateIntoEditext() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        birthDate.setText(sdf.format(myCalendar.getTime()));
    }

    public void showFields() {
        containerFields.setVisibility(View.VISIBLE);
    }
    public void hideFields() {
        containerFields.setVisibility(View.GONE);
    }

    public void hideTextSelectElements() {
        tvSelectElement.setVisibility(View.GONE);
    }
    public void showTextSelectElements() {
        tvSelectElement.setVisibility(View.VISIBLE);
    }

    public void showSaveButton() {
        btnSave.setVisibility(View.VISIBLE);
    }
    public void hideSaveButton() {
        btnSave.setVisibility(View.GONE);
    }

    public void clearFields() {
        name.setText("");
        birthDate.setText("");
    }

    public void hideDetailElement() {
        hideFields();
        hideSaveButton();
        showTextSelectElements();
    }

    public interface Actions extends ViewActions{

        void onSaved(String birthDate, String name);
    }
}
