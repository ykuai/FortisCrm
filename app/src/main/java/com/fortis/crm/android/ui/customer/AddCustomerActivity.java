package com.fortis.crm.android.ui.customer;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.fortis.crm.android.MyApplicationContext;
import com.fortis.crm.android.R;
import com.fortis.crm.android.data.entity.Customer;
import com.fortis.crm.android.repository.Result;

public class AddCustomerActivity extends AppCompatActivity {

    private EditText customerNameEdit;
    private EditText customerOrganizationCodeEdit;
    private EditText sourceEdit;
    private EditText beginOrganizationCertificatePeriodEdit;
    private EditText endOrganizationCertificatePeriodEdit;
    private Button backButton;
    private Button submitButton;
    private AlertDialog alert;
    private AddCustomerViewModel addCustomerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        addCustomerViewModel = new ViewModelProvider(this).get(AddCustomerViewModel.class);

        // 设置标题栏
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(R.string.add_customer_label);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        customerNameEdit = findViewById(R.id.customer_name);
        customerOrganizationCodeEdit = findViewById(R.id.customer_organization_code);
        sourceEdit = findViewById(R.id.source);
        beginOrganizationCertificatePeriodEdit = findViewById(R.id.beginOrganizationCertificatePeriod);
        endOrganizationCertificatePeriodEdit = findViewById(R.id.endOrganizationCertificatePeriod);
        backButton = findViewById(R.id.button_back);
        submitButton = findViewById(R.id.button_submit);

        final String[] items = {"CATL", "国轩高科", "联想", "正泰"};
        final String title = "客户来源";
        sourceEdit.setKeyListener(null);
        sourceEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    alert = new AlertDialog.Builder(AddCustomerActivity.this)
                            .setTitle(title)
                            .setItems(items, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    sourceEdit.setText(items[i]);
                                    alert.dismiss();
                                }
                            }).create();
                    alert.show();
                }
            }
        });
        sourceEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert = new AlertDialog.Builder(AddCustomerActivity.this)
                        .setTitle(title)
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                sourceEdit.setText(items[i]);
                                alert.dismiss();
                            }
                        }).create();
                alert.show();
            }
        });

        addCustomerViewModel.getFormState().observeForever(new Observer<AddCustomerFormState>() {
            @Override
            public void onChanged(AddCustomerFormState addCustomerFormState) {
                if (addCustomerFormState == null) {
                    return;
                }
                submitButton.setEnabled(addCustomerFormState.isDataValid());
                if (addCustomerFormState.getCustomerNameError() != null) {
                    customerNameEdit.setError(getString(addCustomerFormState.getCustomerNameError()));
                }
                if (addCustomerFormState.getCustomerOrganizationCodeError() != null) {
                    customerOrganizationCodeEdit.setError(getString(addCustomerFormState.getCustomerOrganizationCodeError()));
                }
                if (addCustomerFormState.getSourceError() != null) {
                    sourceEdit.setError(getString(addCustomerFormState.getSourceError()));
                }else {
                    sourceEdit.setError(null);
                }
                if (addCustomerFormState.getBeginOrganizationCertificatePeriodError() != null) {
                    beginOrganizationCertificatePeriodEdit.setError(getString(addCustomerFormState.getBeginOrganizationCertificatePeriodError()));
                }
                if (addCustomerFormState.getEndOrganizationCertificatePeriodError() != null) {
                    endOrganizationCertificatePeriodEdit.setError(getString(addCustomerFormState.getEndOrganizationCertificatePeriodError()));
                }
            }
        });
        addCustomerViewModel.getResult().observeForever(new Observer<Result<String>>() {
            @Override
            public void onChanged(Result<String> result) {
                if (result == null) {
                    return;
                }
                if (result instanceof Result.Success) {
                    setResult(Activity.RESULT_OK);
                    finish();
                    Toast.makeText(MyApplicationContext.getContext(), "添加客户成功", Toast.LENGTH_SHORT).show();
                } else if (result instanceof Result.Error) {
                    Toast.makeText(MyApplicationContext.getContext(), ((Result.Error) result).getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                addCustomerViewModel.customerDataChanged(buildCustomer());
            }
        };
        customerNameEdit.addTextChangedListener(afterTextChangedListener);
        customerOrganizationCodeEdit.addTextChangedListener(afterTextChangedListener);
        sourceEdit.addTextChangedListener(afterTextChangedListener);
        beginOrganizationCertificatePeriodEdit.addTextChangedListener(afterTextChangedListener);
        endOrganizationCertificatePeriodEdit.addTextChangedListener(afterTextChangedListener);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        submitButton.setEnabled(false);
        submitButton.setOnClickListener(v -> {addCustomerViewModel.addCustomer(buildCustomer());});
        backButton.setOnClickListener((v -> {finish();}));
    }

    private Customer buildCustomer() {
        Customer customer = new Customer();
        customer.setCustomerName(customerNameEdit.getText().toString());
        customer.setCustomerOrganizationCode(customerOrganizationCodeEdit.getText().toString());
        customer.setSource(sourceEdit.getText().toString());
        customer.setBeginOrganizationCertificatePeriod(beginOrganizationCertificatePeriodEdit.getText().toString());
        customer.setEndOrganizationCertificatePeriod(endOrganizationCertificatePeriodEdit.getText().toString());
        return customer;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 返回箭头
        if (item.getItemId() == android.R.id.home) {
            finish();// finish your activity
        }
        return super.onOptionsItemSelected(item);
    }
}