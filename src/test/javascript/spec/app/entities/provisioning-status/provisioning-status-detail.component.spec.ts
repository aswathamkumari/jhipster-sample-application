/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ProvisioningStatusDetailComponent } from 'app/entities/provisioning-status/provisioning-status-detail.component';
import { ProvisioningStatus } from 'app/shared/model/provisioning-status.model';

describe('Component Tests', () => {
    describe('ProvisioningStatus Management Detail Component', () => {
        let comp: ProvisioningStatusDetailComponent;
        let fixture: ComponentFixture<ProvisioningStatusDetailComponent>;
        const route = ({ data: of({ provisioningStatus: new ProvisioningStatus(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ProvisioningStatusDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ProvisioningStatusDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProvisioningStatusDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.provisioningStatus).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
