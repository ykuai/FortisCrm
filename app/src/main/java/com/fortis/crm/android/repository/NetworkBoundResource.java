package com.fortis.crm.android.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

public abstract class NetworkBoundResource<T> {

    private MediatorLiveData<T> result = new MediatorLiveData<T>();

    public NetworkBoundResource() {
        init();
    }

    private void init() {
        LiveData<T> dbSource = loadFromDb();

        result.addSource(dbSource,(data)->{
            result.removeSource(dbSource);
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource);
            } else {
                result.addSource(dbSource,newData -> setValue(newData));
            }
        });
    }
    private void setValue(T newValue) {
        if (result.getValue() != newValue) {
            result.setValue(newValue);
        }
    }

    protected void fetchFromNetwork(LiveData<T> dbSource){
        result.addSource(dbSource,newData -> setValue(newData));
        LiveData<Result<T>> response = createCall();
        result.addSource(response,(Result<T> r)->{
            result.removeSource(response);
            if (r instanceof Result.Success){
                saveCallResult(processResponse((Result.Success<T>) r));
            } else if (r instanceof Result.Error){
                onFetchFailed(((Result.Error) r).getErrorMessage());
            }
        });
    }

    protected void onFetchFailed(String errorMessage) {
    }

    public LiveData<T> asLiveData() {
        return result;
    }

    protected T processResponse(Result.Success<T> result) {
        return result.getData();
    }

    protected abstract void saveCallResult(T data);

    protected abstract boolean shouldFetch(T result);

    protected abstract LiveData<T> loadFromDb();

    protected abstract LiveData<Result<T>> createCall();

}
