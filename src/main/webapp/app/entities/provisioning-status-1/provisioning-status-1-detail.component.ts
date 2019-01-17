import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProvisioningStatus1 } from 'app/shared/model/provisioning-status-1.model';

@Component({
    selector: 'jhi-provisioning-status-1-detail',
    templateUrl: './provisioning-status-1-detail.component.html'
})
export class ProvisioningStatus1DetailComponent implements OnInit {
    provisioningStatus1: IProvisioningStatus1;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ provisioningStatus1 }) => {
            this.provisioningStatus1 = provisioningStatus1;
        });
    }

    previousState() {
        window.history.back();
    }
}
