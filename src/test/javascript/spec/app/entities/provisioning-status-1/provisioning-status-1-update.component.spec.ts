/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ProvisioningStatus1UpdateComponent } from 'app/entities/provisioning-status-1/provisioning-status-1-update.component';
import { ProvisioningStatus1Service } from 'app/entities/provisioning-status-1/provisioning-status-1.service';
import { ProvisioningStatus1 } from 'app/shared/model/provisioning-status-1.model';

describe('Component Tests', () => {
    describe('ProvisioningStatus1 Management Update Component', () => {
        let comp: ProvisioningStatus1UpdateComponent;
        let fixture: ComponentFixture<ProvisioningStatus1UpdateComponent>;
        let service: ProvisioningStatus1Service;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ProvisioningStatus1UpdateComponent]
            })
                .overrideTemplate(ProvisioningStatus1UpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProvisioningStatus1UpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProvisioningStatus1Service);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ProvisioningStatus1(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.provisioningStatus1 = entity;
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
                    const entity = new ProvisioningStatus1();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.provisioningStatus1 = entity;
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
