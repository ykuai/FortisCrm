package com.fortis.crm.android.ui.customer;

import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.fortis.crm.android.data.entity.Customer;
import com.fortis.crm.android.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CustomerViewModel extends ViewModel {

    private MediatorLiveData<List<Customer>> customers = new MediatorLiveData<>();
    private LiveData<List<String>> firstLetters = Transformations.map(customers, customerList -> {
        Set<String> firstLetterSet = new TreeSet<>();
        for (Customer customer : customerList) {
            firstLetterSet.add(customer.getCustomerNameFirstLetter());
        }
        List<String> result = new ArrayList<>();
        result.add("+");
        result.addAll(firstLetterSet);
        return result;
    });
    CustomerRepository customerRepository = CustomerRepository.getInstance();

    public void setCustomerList(List<Customer> customers) {
        getCustomers().setValue(customers);
    }


    public MediatorLiveData<List<Customer>> getCustomers() {
        return customers;
    }

    public LiveData<List<String>> getFirstLetters() {
        return firstLetters;
    }

    public void loadCustomer(String query) {
        customers.addSource(customerRepository.loadCustomerList(), new Observer<List<Customer>>() {
            @Override
            public void onChanged(List<Customer> customers) {
                setCustomerList(customers);
            }
        });
//
//
//
//        List<Customer> customerList = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            String[] strings = new String[]{"东莞市泰亚电子科技有限公司", "深圳市镭煜科技有限公司", "浙江海迈材料科技股份有限公司", "安徽星凯生态建设有限公司"};
//            Customer customer = new Customer(strings[i % strings.length] + i, "CATL", "初始");
//            customer.setCustomerNo(100000 + i + "");
//            if (TextUtils.isEmpty(query) || customer.getCustomerName().contains(query)) {
//                customerList.add(customer);
//            }
//        }
//        setCustomerList(customerList);
    }
}
