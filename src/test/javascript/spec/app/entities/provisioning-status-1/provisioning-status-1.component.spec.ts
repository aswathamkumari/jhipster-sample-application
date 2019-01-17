/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ProvisioningStatus1Component } from 'app/entities/provisioning-status-1/provisioning-status-1.component';
import { ProvisioningStatus1Service } from 'app/entities/provisioning-status-1/provisioning-status-1.service';
import { ProvisioningStatus1 } from 'app/shared/model/provisioning-status-1.model';

describe('Component Tests', () => {
    describe('ProvisioningStatus1 Management Component', () => {
        let comp: ProvisioningStatus1Component;
        let fixture: ComponentFixture<ProvisioningStatus1Component>;
        let service: ProvisioningStatus1Service;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ProvisioningStatus1Component],
                providers: []
            })
                .overrideTemplate(ProvisioningStatus1Component, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProvisioningStatus1Component);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProvisioningStatus1Service);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ProvisioningStatus1(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.provisioningStatus1S[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
