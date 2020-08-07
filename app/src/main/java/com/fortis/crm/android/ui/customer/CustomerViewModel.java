package com.fortis.crm.android.ui.customer;

import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.fortis.crm.android.ui.customer.dummy.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CustomerViewModel extends ViewModel {

    private MutableLiveData<List<Customer>> customers = new MutableLiveData<>();
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

    public void setCustomerList(List<Customer> customers) {
        getCustomers().setValue(customers);
    }


    public MutableLiveData<List<Customer>> getCustomers() {
        return customers;
    }

    public LiveData<List<String>> getFirstLetters() {
        return firstLetters;
    }

    public void loadCustomer(String query) {
        List<Customer> customerList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String[] strings = new String[]{"东莞市泰亚电子科技有限公司", "深圳市镭煜科技有限公司", "浙江海迈材料科技股份有限公司", "安徽星凯生态建设有限公司"};
            Customer customer = new Customer(10000 + i + "", strings[i % strings.length] + i, "");
            if (i % 5 == 0) {
                customer.setRemark(i + "remark,过去式 grayed过去分词");
            }
            if (TextUtils.isEmpty(query) || customer.getCustomerName().contains(query)) {
                customerList.add(customer);
            }
        }
        setCustomerList(customerList);
    }


}
