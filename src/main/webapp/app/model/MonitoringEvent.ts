export interface IMonitoringEvent {
    id?: number,
    type?: string,
    dateTime?: Date,
    duration?: number,
    reason?: string
}

export class MonitoringEvent implements IMonitoringEvent {
    constructor(
        public id?: number,
        public type?: string,
        public dateTime?: Date,
        public duration?: number,
        public reason?: string
    ) {
    }
}