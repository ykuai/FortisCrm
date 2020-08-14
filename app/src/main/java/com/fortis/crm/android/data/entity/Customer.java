package com.fortis.crm.android.data.entity;

import android.text.TextUtils;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.fortis.crm.android.util.PinyinUtil;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Customer {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    /**
     * 客户号
     */
    private String customerNo;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 客户名称拼音首字母
     */
    @Ignore
    private String customerNameFirstLetter;

    /**
     * 客户组织机构代码
     */
    private String customerOrganizationCode;

    /**
     * 客户来源
     */
    private String source;

    /**
     * 组织机构代码证有效期起始日
     */
    private String beginOrganizationCertificatePeriod;

    /**
     * 组织机构代码证有效期结束日
     */
    private String endOrganizationCertificatePeriod;

    /**
     * 营业执照号
     */
    private String businessLicenceNo;

    /**
     * 税号
     */
    private String taxNo;
    /**
     * 注册日期
     */
    private String registerDate;

    /**
     * 营业期限
     */
    private String operationTerm;

    /**
     * 注册资本
     */
    private String registeredCapital;

    /**
     * 注册地址
     */
    private String registerAddress;

    /**
     * 经营地址
     */
    private String operateAddress;

    /**
     * 企业电话
     */
    private String enterprisePhone;

    /**
     * 企业类型
     */
    private String enterpriseType;

    /**
     * 所属行业
     */
    private String enterpriseIndustry;

    /**
     * 申请信用额度
     */
    private String applyCreditAmount;

    /**
     * 其他银行总授信额度
     */
    private String totalCreditAmountOtherbank;


    private String status;

    @Ignore
    public Customer(String customerName, String source, String status) {
        this.customerName = customerName;
        this.source = source;
        this.status = status;
    }

    public Customer() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

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
        if (customerNameFirstLetter == null && !TextUtils.isEmpty(customerName)) {
            customerNameFirstLetter = PinyinUtil.getFistLetter(customerName.charAt(0));
        }
        return customerNameFirstLetter;
    }

    public void setCustomerNameFirstLetter(String customerNameFirstLetter) {
        this.customerNameFirstLetter = customerNameFirstLetter;
    }

    public String getCustomerOrganizationCode() {
        return customerOrganizationCode;
    }

    public void setCustomerOrganizationCode(String customerOrganizationCode) {
        this.customerOrganizationCode = customerOrganizationCode;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getBeginOrganizationCertificatePeriod() {
        return beginOrganizationCertificatePeriod;
    }

    public void setBeginOrganizationCertificatePeriod(String beginOrganizationCertificatePeriod) {
        this.beginOrganizationCertificatePeriod = beginOrganizationCertificatePeriod;
    }

    public String getEndOrganizationCertificatePeriod() {
        return endOrganizationCertificatePeriod;
    }

    public void setEndOrganizationCertificatePeriod(String endOrganizationCertificatePeriod) {
        this.endOrganizationCertificatePeriod = endOrganizationCertificatePeriod;
    }

    public String getBusinessLicenceNo() {
        return businessLicenceNo;
    }

    public void setBusinessLicenceNo(String businessLicenceNo) {
        this.businessLicenceNo = businessLicenceNo;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getOperationTerm() {
        return operationTerm;
    }

    public void setOperationTerm(String operationTerm) {
        this.operationTerm = operationTerm;
    }

    public String getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(String registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

    public String getOperateAddress() {
        return operateAddress;
    }

    public void setOperateAddress(String operateAddress) {
        this.operateAddress = operateAddress;
    }

    public String getEnterprisePhone() {
        return enterprisePhone;
    }

    public void setEnterprisePhone(String enterprisePhone) {
        this.enterprisePhone = enterprisePhone;
    }

    public String getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(String enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public String getEnterpriseIndustry() {
        return enterpriseIndustry;
    }

    public void setEnterpriseIndustry(String enterpriseIndustry) {
        this.enterpriseIndustry = enterpriseIndustry;
    }

    public String getApplyCreditAmount() {
        return applyCreditAmount;
    }

    public void setApplyCreditAmount(String applyCreditAmount) {
        this.applyCreditAmount = applyCreditAmount;
    }

    public String getTotalCreditAmountOtherbank() {
        return totalCreditAmountOtherbank;
    }

    public void setTotalCreditAmountOtherbank(String totalCreditAmountOtherbank) {
        this.totalCreditAmountOtherbank = totalCreditAmountOtherbank;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return customerName;
    }
}
