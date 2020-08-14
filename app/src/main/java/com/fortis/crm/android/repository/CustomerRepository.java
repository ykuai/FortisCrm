package com.fortis.crm.android.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.fortis.crm.android.AppExecutors;
import com.fortis.crm.android.data.AppDatabase;
import com.fortis.crm.android.data.dao.CustomerDao;
import com.fortis.crm.android.data.entity.Customer;
import com.fortis.crm.android.service.CustomerService;

import java.util.List;

public class CustomerRepository {
    private CustomerDao customerDao = AppDatabase.getDatabase().customerDao();
    private CustomerService customerService = new CustomerService();
    private static volatile CustomerRepository instance;

    /**
     * 单例模式
     */
    private CustomerRepository() {
    }

    public static CustomerRepository getInstance() {
        if (instance == null) {
            instance = new CustomerRepository();
        }
        return instance;
    }

    public LiveData<List<Customer>> loadCustomerList() {
        LiveData<List<Customer>> result = new NetworkBoundResource<List<Customer>>() {

            @Override
            protected void saveCallResult(List<Customer> data) {
                AppExecutors.DISK_IO.execute(() -> {
                    customerDao.insert(data.toArray(new Customer[0]));
                });
            }

            @Override
            protected boolean shouldFetch(List<Customer> result) {
                return true;
            }

            @Override
            protected LiveData<List<Customer>> loadFromDb() {
                return customerDao.loadList();
            }

            @Override
            protected LiveData<Result<List<Customer>>> createCall() {
                return customerService.getCustomerList();
            }
        }.asLiveData();
        return result;
    }

    public LiveData<Result<String>> addCustomer(Customer customer) {
        MediatorLiveData<Result<String>> result = new MediatorLiveData<>();
        result.setValue(new Result.Loading());
        // 根据客户名称查询客户
        LiveData<Customer> oldCustomerLiveData = customerDao.getByCustomerName(customer.getCustomerName());
        result.addSource(oldCustomerLiveData, new Observer<Customer>() {
            @Override
            public void onChanged(Customer oldCustomer) {
                result.removeSource(oldCustomerLiveData);
                // 如果重复则添加客户失败
                if (oldCustomer != null) {
                    result.setValue(new Result.Error("添加失败，客户已存在"));
                } else {
                    // 调用远程服务添加客户
                    LiveData<Result<String>> resultLiveData = customerService.addCustomer(customer);
                    // 监听远程服务添加客户结果，如果添加成功则调用本地数据库添加客户
                    result.addSource(resultLiveData, new Observer<Result<String>>() {
                        @Override
                        public void onChanged(Result<String> result1) {
                            result.removeSource(resultLiveData);
                            if (result1 instanceof Result.Success) {
                                AppExecutors.DISK_IO.execute(() -> {
                                    customerDao.insert(customer);
                                });
                            }
                            result.setValue(result1);
                        }
                    });
                }
            }
        });

        AppExecutors.DISK_IO.execute(() -> {


        });
        return result;


    }

}
