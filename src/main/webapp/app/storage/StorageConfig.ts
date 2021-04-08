import Vuex from "vuex";
import {accountStore} from './AccountStorage'

export function initVueXStore(vue) {
    vue.use(Vuex);
    return new Vuex.Store({
        modules: {
            accountStore
        },
    });
}
