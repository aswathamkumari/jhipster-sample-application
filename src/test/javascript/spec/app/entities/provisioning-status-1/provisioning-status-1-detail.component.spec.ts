/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ProvisioningStatus1DetailComponent } from 'app/entities/provisioning-status-1/provisioning-status-1-detail.component';
import { ProvisioningStatus1 } from 'app/shared/model/provisioning-status-1.model';

describe('Component Tests', () => {
    describe('ProvisioningStatus1 Management Detail Component', () => {
        let comp: ProvisioningStatus1DetailComponent;
        let fixture: ComponentFixture<ProvisioningStatus1DetailComponent>;
        const route = ({ data: of({ provisioningStatus1: new ProvisioningStatus1(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ProvisioningStatus1DetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ProvisioningStatus1DetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProvisioningStatus1DetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.provisioningStatus1).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
