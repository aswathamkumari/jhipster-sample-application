import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProvisioningStatus } from 'app/shared/model/provisioning-status.model';

@Component({
    selector: 'jhi-provisioning-status-detail',
    templateUrl: './provisioning-status-detail.component.html'
})
export class ProvisioningStatusDetailComponent implements OnInit {
    provisioningStatus: IProvisioningStatus;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ provisioningStatus }) => {
            this.provisioningStatus = provisioningStatus;
        });
    }

    previousState() {
        window.history.back();
    }
}
