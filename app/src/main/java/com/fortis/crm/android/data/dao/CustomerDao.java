package com.fortis.crm.android.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.fortis.crm.android.data.entity.Customer;

import java.util.List;

@Dao
public interface CustomerDao {
    @Query("SELECT * FROM customer")
    LiveData<List<Customer>> loadList();

    @Query("SELECT * FROM customer WHERE uid = :uid")
    LiveData<Customer> getById(int uid);

    @Query("SELECT * FROM customer WHERE customerNo = :customerNo LIMIT 1")
    LiveData<Customer> getByCustomerNo(String customerNo);

    @Query("SELECT * FROM customer WHERE customerName = :customerName LIMIT 1")
    LiveData<Customer> getByCustomerName(String customerName);

    @Insert
    void insert(Customer... customers);

    @Delete
    void delete(Customer customer);

    @Update
    void update(Customer... customers);
}
