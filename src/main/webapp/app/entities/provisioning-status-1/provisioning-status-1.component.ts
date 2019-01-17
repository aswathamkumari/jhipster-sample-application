import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IProvisioningStatus1 } from 'app/shared/model/provisioning-status-1.model';
import { AccountService } from 'app/core';
import { ProvisioningStatus1Service } from './provisioning-status-1.service';

@Component({
    selector: 'jhi-provisioning-status-1',
    templateUrl: './provisioning-status-1.component.html'
})
export class ProvisioningStatus1Component implements OnInit, OnDestroy {
    provisioningStatus1S: IProvisioningStatus1[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected provisioningStatus1Service: ProvisioningStatus1Service,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.provisioningStatus1Service.query().subscribe(
            (res: HttpResponse<IProvisioningStatus1[]>) => {
                this.provisioningStatus1S = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInProvisioningStatus1S();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IProvisioningStatus1) {
        return item.id;
    }

    registerChangeInProvisioningStatus1S() {
        this.eventSubscriber = this.eventManager.subscribe('provisioningStatus1ListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
