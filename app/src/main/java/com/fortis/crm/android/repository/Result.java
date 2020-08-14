package com.fortis.crm.android.repository;

/**
 * A generic class that holds a result success w/ data or an error exception.
 */
public class Result<T> {
    private Result() {
    }

    @Override
    public String toString() {
        if (this instanceof Result.Success) {
            Result.Success success = (Result.Success) this;
            return "Success[data=" + success.getData().toString() + "]";
        } else if (this instanceof Result.Error) {
            Result.Error error = (Result.Error) this;
            return "Error[exception=" + error.getErrorMessage() + "]";
        }
        return "";
    }

    // Success sub-class
    public final static class Success<T> extends Result {
        private T data;

        public Success(T data) {
            this.data = data;
        }

        public T getData() {
            return this.data;
        }
    }

    // Error sub-class
    public final static class Error extends Result {
        private String errorMessage;
        private Exception exception;

        public Error(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage() {
            return this.errorMessage;
        }
        public void setException(Exception e){
            this.exception = e;
        }

        public Exception getException() {
            return exception;
        }
    }

    public final static class Loading extends Result {
        public Loading(){}
    }
}