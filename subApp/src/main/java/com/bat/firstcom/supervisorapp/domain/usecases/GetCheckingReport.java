package com.bat.firstcom.supervisorapp.domain.usecases;

import com.bat.firstcom.supervisorapp.datalayer.model.CheckingReportResponse;
import com.bat.firstcom.supervisorapp.domain.repository.SupAppRepository;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Tung Phan on 27-Jul-17.
 */

public class GetCheckingReport extends UseCase<CheckingReportResponse, GetCheckingReport.Params> {

    private SupAppRepository repository;

    public GetCheckingReport(SupAppRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    Observable<CheckingReportResponse> buildUseCaseObservable(Params params) {
        return repository.getCheckingReport(params.pstId, params.authorizationToken, params.brand);
    }

    @Override
    Single<CheckingReportResponse> buildUseCaseSingle(Params params) {
        return null;
    }

    @Override
    Completable buildUseCaseCompletable(Params params) {
        return null;
    }

    public static final class Params {

        private final String pstId;
        private final int brand;
        private final String authorizationToken;

        private Params(String pstId, String authorizationToken, int brand) {
            this.pstId = pstId;
            this.brand = brand;
            this.authorizationToken = authorizationToken;
        }

        public static Params forGetCheckingReport(String pstId, String authorizationToken, int brand) {
            return new Params(pstId, authorizationToken, brand);
        }
    }
}