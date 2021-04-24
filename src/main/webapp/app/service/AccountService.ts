import axios from 'axios';
import {Store} from 'vuex';
import VueRouter from "vue-router";

export default class AccountService {
    constructor(private store: Store<any>,
                private router: VueRouter) {
    }

    public retrieveAccount(): Promise<boolean> {
        return new Promise(resolve => {
            axios
                .get('/api/account')
                .then(response => {
                    this.store.commit('authenticate');
                    const account = response.data;
                    if (account) {
                        this.store.commit('authenticated', account);
                        if (sessionStorage.getItem('requested-url')) {
                            this.router.replace(sessionStorage.getItem('requested-url'));
                            sessionStorage.removeItem('requested-url');
                        }
                    } else {
                        this.store.commit('logout');
                        this.router.push('/');
                        sessionStorage.removeItem('requested-url');
                    }
                    resolve(true);
                })
                .catch(() => {
                    this.store.commit('logout');
                    resolve(false);
                });
        });
    }

    public hasAnyAuthorityAndCheckAuth(authorities: any): Promise<boolean> {
        if (typeof authorities === 'string') {
            authorities = [authorities];
        }

        if (!this.authenticated || !this.userAuthorities) {
            const token = localStorage.getItem('AuthenticationToken');
            if (!this.store.getters.account && !this.store.getters.logon && token) {
                return this.retrieveAccount();
            } else {
                return new Promise(resolve => {
                    resolve(false);
                });
            }
        }

        // for (let i = 0; i < authorities.length; i++) {
        //     if (this.userAuthorities.includes(authorities[i])) {
        //         return new Promise(resolve => {
        //             resolve(true);
        //         });
        //     }
        // }
        //
        // return new Promise(resolve => {
        //     resolve(false);
        // });
        return new Promise(resolve => {
            resolve(true);
        });
    }

    public get authenticated(): boolean {
        return this.store.getters.authenticated;
    }

    public get userAuthorities(): any {
        return this.store.getters.account.authorities;
    }
}