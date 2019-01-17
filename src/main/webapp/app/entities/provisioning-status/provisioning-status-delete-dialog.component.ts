import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProvisioningStatus } from 'app/shared/model/provisioning-status.model';
import { ProvisioningStatusService } from './provisioning-status.service';

@Component({
    selector: 'jhi-provisioning-status-delete-dialog',
    templateUrl: './provisioning-status-delete-dialog.component.html'
})
export class ProvisioningStatusDeleteDialogComponent {
    provisioningStatus: IProvisioningStatus;

    constructor(
        protected provisioningStatusService: ProvisioningStatusService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.provisioningStatusService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'provisioningStatusListModification',
                content: 'Deleted an provisioningStatus'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-provisioning-status-delete-popup',
    template: ''
})
export class ProvisioningStatusDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ provisioningStatus }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ProvisioningStatusDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.provisioningStatus = provisioningStatus;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
