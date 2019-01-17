import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IProvisioningStatus1 } from 'app/shared/model/provisioning-status-1.model';
import { ProvisioningStatus1Service } from './provisioning-status-1.service';

@Component({
    selector: 'jhi-provisioning-status-1-update',
    templateUrl: './provisioning-status-1-update.component.html'
})
export class ProvisioningStatus1UpdateComponent implements OnInit {
    provisioningStatus1: IProvisioningStatus1;
    isSaving: boolean;

    constructor(protected provisioningStatus1Service: ProvisioningStatus1Service, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ provisioningStatus1 }) => {
            this.provisioningStatus1 = provisioningStatus1;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.provisioningStatus1.id !== undefined) {
            this.subscribeToSaveResponse(this.provisioningStatus1Service.update(this.provisioningStatus1));
        } else {
            this.subscribeToSaveResponse(this.provisioningStatus1Service.create(this.provisioningStatus1));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IProvisioningStatus1>>) {
        result.subscribe((res: HttpResponse<IProvisioningStatus1>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
