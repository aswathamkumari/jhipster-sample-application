import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IProvisioningStatus } from 'app/shared/model/provisioning-status.model';

type EntityResponseType = HttpResponse<IProvisioningStatus>;
type EntityArrayResponseType = HttpResponse<IProvisioningStatus[]>;

@Injectable({ providedIn: 'root' })
export class ProvisioningStatusService {
    public resourceUrl = SERVER_API_URL + 'api/provisioning-statuses';

    constructor(protected http: HttpClient) {}

    create(provisioningStatus: IProvisioningStatus): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(provisioningStatus);
        return this.http
            .post<IProvisioningStatus>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(provisioningStatus: IProvisioningStatus): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(provisioningStatus);
        return this.http
            .put<IProvisioningStatus>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IProvisioningStatus>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IProvisioningStatus[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(provisioningStatus: IProvisioningStatus): IProvisioningStatus {
        const copy: IProvisioningStatus = Object.assign({}, provisioningStatus, {
            timestamp:
                provisioningStatus.timestamp != null && provisioningStatus.timestamp.isValid()
                    ? provisioningStatus.timestamp.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.timestamp = res.body.timestamp != null ? moment(res.body.timestamp) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((provisioningStatus: IProvisioningStatus) => {
                provisioningStatus.timestamp = provisioningStatus.timestamp != null ? moment(provisioningStatus.timestamp) : null;
            });
        }
        return res;
    }
}
