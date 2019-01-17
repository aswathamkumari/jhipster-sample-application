import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IProvisioningStatus1 } from 'app/shared/model/provisioning-status-1.model';

type EntityResponseType = HttpResponse<IProvisioningStatus1>;
type EntityArrayResponseType = HttpResponse<IProvisioningStatus1[]>;

@Injectable({ providedIn: 'root' })
export class ProvisioningStatus1Service {
    public resourceUrl = SERVER_API_URL + 'api/provisioning-status-1-s';

    constructor(protected http: HttpClient) {}

    create(provisioningStatus1: IProvisioningStatus1): Observable<EntityResponseType> {
        return this.http.post<IProvisioningStatus1>(this.resourceUrl, provisioningStatus1, { observe: 'response' });
    }

    update(provisioningStatus1: IProvisioningStatus1): Observable<EntityResponseType> {
        return this.http.put<IProvisioningStatus1>(this.resourceUrl, provisioningStatus1, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IProvisioningStatus1>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IProvisioningStatus1[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
