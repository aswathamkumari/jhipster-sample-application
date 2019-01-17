import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ProvisioningStatus1 } from 'app/shared/model/provisioning-status-1.model';
import { ProvisioningStatus1Service } from './provisioning-status-1.service';
import { ProvisioningStatus1Component } from './provisioning-status-1.component';
import { ProvisioningStatus1DetailComponent } from './provisioning-status-1-detail.component';
import { ProvisioningStatus1UpdateComponent } from './provisioning-status-1-update.component';
import { ProvisioningStatus1DeletePopupComponent } from './provisioning-status-1-delete-dialog.component';
import { IProvisioningStatus1 } from 'app/shared/model/provisioning-status-1.model';

@Injectable({ providedIn: 'root' })
export class ProvisioningStatus1Resolve implements Resolve<IProvisioningStatus1> {
    constructor(private service: ProvisioningStatus1Service) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ProvisioningStatus1> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ProvisioningStatus1>) => response.ok),
                map((provisioningStatus1: HttpResponse<ProvisioningStatus1>) => provisioningStatus1.body)
            );
        }
        return of(new ProvisioningStatus1());
    }
}

export const provisioningStatus1Route: Routes = [
    {
        path: 'provisioning-status-1',
        component: ProvisioningStatus1Component,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProvisioningStatus1S'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'provisioning-status-1/:id/view',
        component: ProvisioningStatus1DetailComponent,
        resolve: {
            provisioningStatus1: ProvisioningStatus1Resolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProvisioningStatus1S'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'provisioning-status-1/new',
        component: ProvisioningStatus1UpdateComponent,
        resolve: {
            provisioningStatus1: ProvisioningStatus1Resolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProvisioningStatus1S'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'provisioning-status-1/:id/edit',
        component: ProvisioningStatus1UpdateComponent,
        resolve: {
            provisioningStatus1: ProvisioningStatus1Resolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProvisioningStatus1S'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const provisioningStatus1PopupRoute: Routes = [
    {
        path: 'provisioning-status-1/:id/delete',
        component: ProvisioningStatus1DeletePopupComponent,
        resolve: {
            provisioningStatus1: ProvisioningStatus1Resolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProvisioningStatus1S'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
