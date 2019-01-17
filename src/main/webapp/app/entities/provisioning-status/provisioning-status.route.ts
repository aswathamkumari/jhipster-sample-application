import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ProvisioningStatus } from 'app/shared/model/provisioning-status.model';
import { ProvisioningStatusService } from './provisioning-status.service';
import { ProvisioningStatusComponent } from './provisioning-status.component';
import { ProvisioningStatusDetailComponent } from './provisioning-status-detail.component';
import { ProvisioningStatusUpdateComponent } from './provisioning-status-update.component';
import { ProvisioningStatusDeletePopupComponent } from './provisioning-status-delete-dialog.component';
import { IProvisioningStatus } from 'app/shared/model/provisioning-status.model';

@Injectable({ providedIn: 'root' })
export class ProvisioningStatusResolve implements Resolve<IProvisioningStatus> {
    constructor(private service: ProvisioningStatusService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ProvisioningStatus> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ProvisioningStatus>) => response.ok),
                map((provisioningStatus: HttpResponse<ProvisioningStatus>) => provisioningStatus.body)
            );
        }
        return of(new ProvisioningStatus());
    }
}

export const provisioningStatusRoute: Routes = [
    {
        path: 'provisioning-status',
        component: ProvisioningStatusComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProvisioningStatuses'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'provisioning-status/:id/view',
        component: ProvisioningStatusDetailComponent,
        resolve: {
            provisioningStatus: ProvisioningStatusResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProvisioningStatuses'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'provisioning-status/new',
        component: ProvisioningStatusUpdateComponent,
        resolve: {
            provisioningStatus: ProvisioningStatusResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProvisioningStatuses'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'provisioning-status/:id/edit',
        component: ProvisioningStatusUpdateComponent,
        resolve: {
            provisioningStatus: ProvisioningStatusResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProvisioningStatuses'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const provisioningStatusPopupRoute: Routes = [
    {
        path: 'provisioning-status/:id/delete',
        component: ProvisioningStatusDeletePopupComponent,
        resolve: {
            provisioningStatus: ProvisioningStatusResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProvisioningStatuses'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
