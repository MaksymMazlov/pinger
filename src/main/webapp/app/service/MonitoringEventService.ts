import {MonitoringEvent} from "../model/MonitoringEvent";
import axios from 'axios';

export default class MonitoringEventService {
    public getEvents(resourceId: number): Promise<MonitoringEvent[]> {
        return new Promise<MonitoringEvent[]>((resolve, reject) => {
            axios.get(`/api/events/${resourceId}`)
                .then(res => resolve(res.data))
                .catch(err => reject(err));
        })
    }
}