package com.fortis.crm.android.ui.customer;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fortis.crm.android.R;
import com.fortis.crm.android.data.entity.Customer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRecyclerViewAdapter extends RecyclerView.Adapter<CustomerRecyclerViewAdapter.ViewHolder> {

    private List<Customer> customerList;
    private Map<String/*首字母*/, Integer/*Customer下标*/> letterPosition;

    public CustomerRecyclerViewAdapter() {
        setCustomerList(null);
    }

    public CustomerRecyclerViewAdapter(List<Customer> customerList) {
        setCustomerList(customerList);
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        // 添加客户操作
        this.customerList = new ArrayList<>();
        Customer addOperation = new Customer();
        addOperation.setCustomerName("添加客户");
        addOperation.setCustomerNameFirstLetter("+");
        this.customerList.add(addOperation);
        // 按拼音首字母排序
        if (customerList != null) {
            Collections.sort(customerList, (o1, o2) -> {
                // TODO 直接按首字母排序，首字母相同时未明确排序
                return o1.getCustomerNameFirstLetter().compareTo(o2.getCustomerNameFirstLetter());

//                if (!o1.getCustomerNameFirstLetter().equals(o2.getCustomerNameFirstLetter())) {
//
//                } else {
//                    return o1.getCustomerNo().compareTo(o2.getCustomerNo());
//                }
            });
            this.customerList.addAll(customerList);
        }
        // 更新UI
        updateLetterPosition();
        notifyDataSetChanged();
    }

    private void updateLetterPosition() {
        letterPosition = new HashMap<>();
        for (int i = customerList.size() - 1; i >= 0; i--) {
            Customer c = customerList.get(i);
            letterPosition.put(c.getCustomerNameFirstLetter(), i);
        }
    }

    public Integer getPositionByLetter(String latter) {
        if (letterPosition.containsKey(latter)) {
            return letterPosition.get(latter);
        }
        return -1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customer_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Customer customer = customerList.get(position);
        holder.mItem = customer;
        holder.contentView.setCompoundDrawables(null, null, null, null);
        holder.remarkView.setVisibility(View.GONE);
        holder.letterView.setVisibility(View.GONE);

        // 设置点击事件
        if (position == 0){
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(holder.mView.getContext(),AddCustomerActivity.class);
                    holder.mView.getContext().startActivity(intent);
                }
            });
        }else {
            holder.mView.setOnClickListener(null);
        }
        // 设置操作项
        if (position == 0) {// 添加客户
            Drawable drawableLeft = ContextCompat.getDrawable(holder.contentView.getContext(), R.drawable.ic_add_customer);
            drawableLeft.setBounds(0, 0, 60, 60);
            holder.contentView.setCompoundDrawables(drawableLeft, null, null, null);
        }
        // 设置字母栏
        else if (position == letterPosition.get(customer.getCustomerNameFirstLetter())) {
            holder.letterView.setVisibility(View.VISIBLE);
            holder.letterView.setText(customer.getCustomerNameFirstLetter());
        }
        // 设置客户名称
        holder.contentView.setText(customer.getCustomerName());
        // 设置备注
        if (!TextUtils.isEmpty(customer.getSource())) {
            holder.remarkView.setVisibility(View.VISIBLE);
            holder.remarkView.setText(customer.getSource());
        }
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView letterView;
        public final TextView contentView;
        public final TextView remarkView;
        public Customer mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            letterView = view.findViewById(R.id.letter);
            contentView = view.findViewById(R.id.content);
            remarkView = view.findViewById(R.id.remark);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + contentView.getText() + "'";
        }
    }
}