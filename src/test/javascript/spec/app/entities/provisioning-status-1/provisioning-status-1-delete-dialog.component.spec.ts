/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ProvisioningStatus1DeleteDialogComponent } from 'app/entities/provisioning-status-1/provisioning-status-1-delete-dialog.component';
import { ProvisioningStatus1Service } from 'app/entities/provisioning-status-1/provisioning-status-1.service';

describe('Component Tests', () => {
    describe('ProvisioningStatus1 Management Delete Component', () => {
        let comp: ProvisioningStatus1DeleteDialogComponent;
        let fixture: ComponentFixture<ProvisioningStatus1DeleteDialogComponent>;
        let service: ProvisioningStatus1Service;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ProvisioningStatus1DeleteDialogComponent]
            })
                .overrideTemplate(ProvisioningStatus1DeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProvisioningStatus1DeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProvisioningStatus1Service);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
