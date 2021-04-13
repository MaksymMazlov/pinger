<template>
  <div>
    <div class="alert alert-danger" v-if="error">
      Ошибка
    </div>

    <div class="row">
      <div class="col w100 my-2">
        <h5>Список ресурсів</h5>
        <p class="text-muted">Це Ваш список ресурсів, доступних для моніторингу. Натисніть "Створити ресурс" для додавання нового ресурсу.</p>
        <router-link class="btn btn-sm btn-success" to="/page/resource/new"><font-awesome-icon icon="plus-square"/> Створити ресурс</router-link>
      </div>
    </div>
    <div class="row row-cols-2">
      <div v-for="resource in resources" :key="resource.id">
        <resource-component v-bind:resource="resource" v-bind:resources="resources">
        </resource-component>
      </div>

    </div>
  </div>
</template>
<script lang="ts">
import Component from 'vue-class-component';
import {Inject, Vue} from 'vue-property-decorator';
import AccountResourceService from "../service/AccountResourceService";
import {IAccountResource} from "../model/AccountResource";
import ResourceComponent from './component/ResourceComponent'

@Component({
  'components': {
    'resource-component': ResourceComponent
  }
})
export default class ResourcesPage extends Vue {
  @Inject('accountResourceService')
  protected accountResourceService: () => AccountResourceService;
  public resources: IAccountResource[] = [];
  public error: boolean = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.init();
    });
  }

  public init(): void {
    this.accountResourceService().getAllResources()
        .then(r => this.resources = r)
        .catch(err => this.error = true);
  }
}
</script>
