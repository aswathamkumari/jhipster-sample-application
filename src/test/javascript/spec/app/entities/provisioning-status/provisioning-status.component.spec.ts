/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ProvisioningStatusComponent } from 'app/entities/provisioning-status/provisioning-status.component';
import { ProvisioningStatusService } from 'app/entities/provisioning-status/provisioning-status.service';
import { ProvisioningStatus } from 'app/shared/model/provisioning-status.model';

describe('Component Tests', () => {
    describe('ProvisioningStatus Management Component', () => {
        let comp: ProvisioningStatusComponent;
        let fixture: ComponentFixture<ProvisioningStatusComponent>;
        let service: ProvisioningStatusService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ProvisioningStatusComponent],
                providers: []
            })
                .overrideTemplate(ProvisioningStatusComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProvisioningStatusComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProvisioningStatusService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ProvisioningStatus(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.provisioningStatuses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
