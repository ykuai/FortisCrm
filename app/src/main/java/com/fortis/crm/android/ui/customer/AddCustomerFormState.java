package com.fortis.crm.android.ui.customer;

import androidx.annotation.Nullable;

public class AddCustomerFormState {

    @Nullable
    private Integer customerNameError;
    @Nullable
    private Integer customerOrganizationCodeError;
    @Nullable
    private Integer sourceError;
    @Nullable
    private Integer beginOrganizationCertificatePeriodError;
    @Nullable
    private Integer endOrganizationCertificatePeriodError;
    private boolean isDataValid;

    @Nullable
    public Integer getCustomerNameError() {
        return customerNameError;
    }

    public void setCustomerNameError(@Nullable Integer customerNameError) {
        this.customerNameError = customerNameError;
    }

    @Nullable
    public Integer getCustomerOrganizationCodeError() {
        return customerOrganizationCodeError;
    }

    public void setCustomerOrganizationCodeError(@Nullable Integer customerOrganizationCodeError) {
        this.customerOrganizationCodeError = customerOrganizationCodeError;
    }

    @Nullable
    public Integer getSourceError() {
        return sourceError;
    }

    public void setSourceError(@Nullable Integer sourceError) {
        this.sourceError = sourceError;
    }

    @Nullable
    public Integer getBeginOrganizationCertificatePeriodError() {
        return beginOrganizationCertificatePeriodError;
    }

    public void setBeginOrganizationCertificatePeriodError(@Nullable Integer beginOrganizationCertificatePeriodError) {
        this.beginOrganizationCertificatePeriodError = beginOrganizationCertificatePeriodError;
    }

    @Nullable
    public Integer getEndOrganizationCertificatePeriodError() {
        return endOrganizationCertificatePeriodError;
    }

    public void setEndOrganizationCertificatePeriodError(@Nullable Integer endOrganizationCertificatePeriodError) {
        this.endOrganizationCertificatePeriodError = endOrganizationCertificatePeriodError;
    }

    public boolean isDataValid() {
        return isDataValid;
    }

    public void setDataValid(boolean dataValid) {
        isDataValid = dataValid;
    }
}
