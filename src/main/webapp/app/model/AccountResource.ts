export interface IAccountResource {
    id?: number;
    name?: string;
    type?: string;
    host?: string;
    monitoringInterval?: number;
    created?: Date;
    accountId?: number;
}

export class AccountResource implements IAccountResource {
    constructor(
        public id?: number,
        public name?: string,
        public type?: string,
        public host?: string,
        public monitoringInterval?: number,
        public created?: Date,
        public accountId?: number) {
    }
}