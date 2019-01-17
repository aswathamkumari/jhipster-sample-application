import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProvisioningStatus1 } from 'app/shared/model/provisioning-status-1.model';
import { ProvisioningStatus1Service } from './provisioning-status-1.service';

@Component({
    selector: 'jhi-provisioning-status-1-delete-dialog',
    templateUrl: './provisioning-status-1-delete-dialog.component.html'
})
export class ProvisioningStatus1DeleteDialogComponent {
    provisioningStatus1: IProvisioningStatus1;

    constructor(
        protected provisioningStatus1Service: ProvisioningStatus1Service,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.provisioningStatus1Service.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'provisioningStatus1ListModification',
                content: 'Deleted an provisioningStatus1'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-provisioning-status-1-delete-popup',
    template: ''
})
export class ProvisioningStatus1DeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ provisioningStatus1 }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ProvisioningStatus1DeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.provisioningStatus1 = provisioningStatus1;
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
