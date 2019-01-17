import { Moment } from 'moment';

export interface IProvisioningStatus {
    id?: number;
    pvsid?: string;
    serialnumber?: string;
    username?: string;
    siteid?: number;
    environment?: string;
    operation?: string;
    status?: number;
    level?: number;
    timestamp?: Moment;
}

export class ProvisioningStatus implements IProvisioningStatus {
    constructor(
        public id?: number,
        public pvsid?: string,
        public serialnumber?: string,
        public username?: string,
        public siteid?: number,
        public environment?: string,
        public operation?: string,
        public status?: number,
        public level?: number,
        public timestamp?: Moment
    ) {}
}
