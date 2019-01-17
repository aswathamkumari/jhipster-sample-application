import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IProvisioningStatus } from 'app/shared/model/provisioning-status.model';
import { AccountService } from 'app/core';
import { ProvisioningStatusService } from './provisioning-status.service';

@Component({
    selector: 'jhi-provisioning-status',
    templateUrl: './provisioning-status.component.html'
})
export class ProvisioningStatusComponent implements OnInit, OnDestroy {
    provisioningStatuses: IProvisioningStatus[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected provisioningStatusService: ProvisioningStatusService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.provisioningStatusService.query().subscribe(
            (res: HttpResponse<IProvisioningStatus[]>) => {
                this.provisioningStatuses = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInProvisioningStatuses();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IProvisioningStatus) {
        return item.id;
    }

    registerChangeInProvisioningStatuses() {
        this.eventSubscriber = this.eventManager.subscribe('provisioningStatusListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
