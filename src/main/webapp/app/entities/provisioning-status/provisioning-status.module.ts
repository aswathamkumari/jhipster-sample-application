import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import {
    ProvisioningStatusComponent,
    ProvisioningStatusDetailComponent,
    ProvisioningStatusUpdateComponent,
    ProvisioningStatusDeletePopupComponent,
    ProvisioningStatusDeleteDialogComponent,
    provisioningStatusRoute,
    provisioningStatusPopupRoute
} from './';

const ENTITY_STATES = [...provisioningStatusRoute, ...provisioningStatusPopupRoute];

@NgModule({
    imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ProvisioningStatusComponent,
        ProvisioningStatusDetailComponent,
        ProvisioningStatusUpdateComponent,
        ProvisioningStatusDeleteDialogComponent,
        ProvisioningStatusDeletePopupComponent
    ],
    entryComponents: [
        ProvisioningStatusComponent,
        ProvisioningStatusUpdateComponent,
        ProvisioningStatusDeleteDialogComponent,
        ProvisioningStatusDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationProvisioningStatusModule {}
