import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IProvisioningStatus } from 'app/shared/model/provisioning-status.model';
import { ProvisioningStatusService } from './provisioning-status.service';

@Component({
    selector: 'jhi-provisioning-status-update',
    templateUrl: './provisioning-status-update.component.html'
})
export class ProvisioningStatusUpdateComponent implements OnInit {
    provisioningStatus: IProvisioningStatus;
    isSaving: boolean;
    timestamp: string;

    constructor(protected provisioningStatusService: ProvisioningStatusService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ provisioningStatus }) => {
            this.provisioningStatus = provisioningStatus;
            this.timestamp = this.provisioningStatus.timestamp != null ? this.provisioningStatus.timestamp.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.provisioningStatus.timestamp = this.timestamp != null ? moment(this.timestamp, DATE_TIME_FORMAT) : null;
        if (this.provisioningStatus.id !== undefined) {
            this.subscribeToSaveResponse(this.provisioningStatusService.update(this.provisioningStatus));
        } else {
            this.subscribeToSaveResponse(this.provisioningStatusService.create(this.provisioningStatus));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IProvisioningStatus>>) {
        result.subscribe((res: HttpResponse<IProvisioningStatus>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
