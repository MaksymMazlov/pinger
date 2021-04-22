import axios from 'axios';
import {AccountResource} from "../model/AccountResource";

export default class AccountResourceService {
    public getAllResources(): Promise<AccountResource[]> {
        return new Promise<AccountResource[]>(((resolve, reject) => {
            axios.get('/api/resources').then(res => resolve(res.data)).catch(err => reject(err));
        }))
    }

    public getResourceById(resourceId: number): Promise<AccountResource> {
        return new Promise<AccountResource>(((resolve, reject) => {
            axios.get(`/api/resource/${resourceId}`).then(res => resolve(res.data)).catch(err => reject(err));
        }))
    }

    public getResourceUptime(resourceId:number): Promise<Number> {
        return new Promise<Number>(((resolve, reject) => {
            axios.get(`/api/resource/${resourceId}/uptime`).then(res => resolve(res.data.uptime)).catch(err => reject(err));
        }))
    }

}