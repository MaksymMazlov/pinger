export interface IMonitoringByWeek {
    id?: number,
    date?: Date,
    status?: string,
    isAvailable?: boolean
}

export class MonitoringByWeek implements IMonitoringByWeek {
    constructor(
        public id?: number,
        public date?: Date,
        public status?: string,
        public isAvailable?: boolean
    ) {
    }
}