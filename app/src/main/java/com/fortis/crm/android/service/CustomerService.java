package com.fortis.crm.android.service;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.fortis.crm.android.AppExecutors;
import com.fortis.crm.android.repository.Result;
import com.fortis.crm.android.data.entity.Customer;

import java.util.ArrayList;
import java.util.List;

import static com.fortis.crm.android.repository.Result.*;

public class CustomerService {

   public LiveData<Result<List<Customer>>> getCustomerList(){
       MutableLiveData<Result<List<Customer>>> resultLiveData = new MutableLiveData<>();
       List<Customer> customers= generateCustomerList();

       Result.Success<List<Customer>> result = new Result.Success<>(customers);
       resultLiveData.setValue(result);
       return resultLiveData;
   }

   public LiveData<Result<String>> addCustomer(Customer customer){
       MutableLiveData<Result<String>> resultMutableLiveData = new MutableLiveData<>();
       resultMutableLiveData.setValue(new Success("SUCCESS"));
       return resultMutableLiveData;
   }

    private List<Customer> generateCustomerList() {
       List<Customer> customers = new ArrayList<>();
        AppExecutors.NETWORK_IO.execute(()->{

        });
        return customers;
    }

}
