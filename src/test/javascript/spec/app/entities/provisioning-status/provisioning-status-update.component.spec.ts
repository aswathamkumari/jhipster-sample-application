/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ProvisioningStatusUpdateComponent } from 'app/entities/provisioning-status/provisioning-status-update.component';
import { ProvisioningStatusService } from 'app/entities/provisioning-status/provisioning-status.service';
import { ProvisioningStatus } from 'app/shared/model/provisioning-status.model';

describe('Component Tests', () => {
    describe('ProvisioningStatus Management Update Component', () => {
        let comp: ProvisioningStatusUpdateComponent;
        let fixture: ComponentFixture<ProvisioningStatusUpdateComponent>;
        let service: ProvisioningStatusService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ProvisioningStatusUpdateComponent]
            })
                .overrideTemplate(ProvisioningStatusUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProvisioningStatusUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProvisioningStatusService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ProvisioningStatus(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.provisioningStatus = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ProvisioningStatus();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.provisioningStatus = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
