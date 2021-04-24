import {MonitoringByWeek} from "../model/MonitoringByWeek";
import axios from 'axios';

export default class MonitoringByWeekService {
    public getWeeklyStatus(resourceId: number): Promise<MonitoringByWeek[]> {
        return new Promise<MonitoringByWeek[]>((resolve, reject) => {
            axios.get(`/api/monitoring/week/${resourceId}`)
                .then(res => resolve(res.data))
                .catch(err => reject(err));
        })
    }
}