package com.fortis.crm.android.ui.customer;

import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.fortis.crm.android.R;
import com.fortis.crm.android.data.entity.Customer;
import com.fortis.crm.android.repository.CustomerRepository;
import com.fortis.crm.android.repository.Result;

public class AddCustomerViewModel extends ViewModel {

    private MutableLiveData<AddCustomerFormState> addCustomerFormStateLiveData = new MutableLiveData<>();
    private MediatorLiveData<Result<String>> addResult = new MediatorLiveData<>();
    private CustomerRepository customerRepository = CustomerRepository.getInstance();

    LiveData<AddCustomerFormState> getFormState() {
        return addCustomerFormStateLiveData;
    }

    LiveData<Result<String>> getResult() {
        return addResult;
    }

    public void addCustomer(Customer customer) {
        LiveData<Result<String>> resultLiveData = customerRepository.addCustomer(customer);
        addResult.addSource(resultLiveData, new Observer<Result<String>>() {
            @Override
            public void onChanged(Result<String> result) {
                addResult.setValue(result);
            }
        });
    }

    public void customerDataChanged(Customer customer) {
        AddCustomerFormState addCustomerFormState = new AddCustomerFormState();
        if (TextUtils.isEmpty(customer.getCustomerName())) {
            addCustomerFormState.setCustomerNameError(R.string.invalid_customer_name);
        } else if (TextUtils.isEmpty(customer.getCustomerOrganizationCode())) {
            addCustomerFormState.setCustomerOrganizationCodeError(R.string.invalid_customer_organization_code);
        } else if (TextUtils.isEmpty(customer.getSource())) {
            addCustomerFormState.setSourceError(R.string.invalid_customer_source);
        } else if (TextUtils.isEmpty(customer.getBeginOrganizationCertificatePeriod())) {
            addCustomerFormState.setBeginOrganizationCertificatePeriodError(R.string.invalid_begin_organization_certificate_period);
        } else if (TextUtils.isEmpty(customer.getEndOrganizationCertificatePeriod())) {
            addCustomerFormState.setEndOrganizationCertificatePeriodError(R.string.invalid_end_organization_certificate_period);
        } else {
            addCustomerFormState.setDataValid(true);
        }
        addCustomerFormStateLiveData.setValue(addCustomerFormState);
    }

}
