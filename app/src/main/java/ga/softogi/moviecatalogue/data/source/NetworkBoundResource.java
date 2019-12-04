package ga.softogi.moviecatalogue.data.source;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import ga.softogi.moviecatalogue.data.source.remote.ApiResponse;
import ga.softogi.moviecatalogue.utils.AppExecutors;
import ga.softogi.moviecatalogue.vo.Resource;

public abstract class NetworkBoundResource<ResultType, RequestType> {

    private MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    private AppExecutors mExecutors;

    NetworkBoundResource(AppExecutors mExecutors) {
        this.mExecutors = mExecutors;
        result.setValue(Resource.loading(null));

        LiveData<ResultType> dbSource = loadFromDb();

        result.addSource(dbSource, data -> {
            result.removeSource(dbSource);
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource);
            } else {
                result.addSource(dbSource, newData -> result.setValue(Resource.success(newData)));
            }
        });
    }

    private void onFetchFailed() {
    }

    protected abstract LiveData<ResultType> loadFromDb();

    protected abstract Boolean shouldFetch(ResultType data);

    protected abstract LiveData<ApiResponse<RequestType>> createCall();

    protected abstract void saveCallResult(RequestType data);

    private void fetchFromNetwork(LiveData<ResultType> dbSource) {

        LiveData<ApiResponse<RequestType>> apiResponse = createCall();

        result.addSource(dbSource, newData ->
                result.setValue(Resource.loading(newData)));
        result.addSource(apiResponse, response -> {
            result.removeSource(apiResponse);
            result.removeSource(dbSource);

            switch (response.status) {
                case SUCCESS:
                    mExecutors.getDiskIO().execute(() -> {
                        saveCallResult(response.body);

                        mExecutors.getMainThread().execute(() -> result.addSource(loadFromDb(),
                                newData -> result.setValue(Resource.success(newData))));
                    });
                    break;

                case EMPTY:
                    mExecutors.getMainThread().execute(() -> result.addSource(loadFromDb(),
                            newData -> result.setValue(Resource.success(newData))));
                    break;

                case ERROR:
                    onFetchFailed();
                    result.addSource(dbSource, newData ->
                            result.setValue(Resource.error(response.message, newData)));
            }
        });
    }

    LiveData<Resource<ResultType>> asLiveData() {
        return result;
    }
}
