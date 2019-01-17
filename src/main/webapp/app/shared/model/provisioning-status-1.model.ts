export interface IProvisioningStatus1 {
    id?: number;
    pvsid?: string;
    serialnumber?: string;
}

export class ProvisioningStatus1 implements IProvisioningStatus1 {
    constructor(public id?: number, public pvsid?: string, public serialnumber?: string) {}
}
