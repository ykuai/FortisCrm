package com.fortis.crm.android.ui.customer.dummy;

import com.fortis.crm.android.util.PinyinUtil;
import com.fortis.crm.android.util.StringUtil;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * A dummy item representing a piece of content.
 */
public class Customer {
    private String customerNo;
    private String customerName;
    private String customerNameFirstLetter;
    private String remark;
    private String details;

    public Customer(String customerNo, String customerName, String remark) {
        this.customerNo = customerNo;
        this.customerName = customerName;
        this.details = remark;
    }

    public Customer(){}

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerNameFirstLetter() {
        if (customerNameFirstLetter == null) {
            if (!StringUtil.isBank(getCustomerName())) {
                customerNameFirstLetter = PinyinUtil.getFistLetter(getCustomerName().charAt(0));
            }
        }
        return customerNameFirstLetter;
    }

    public void setCustomerNameFirstLetter(String customerNameFirstLetter) {
        this.customerNameFirstLetter = customerNameFirstLetter;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return customerName;
    }
}
