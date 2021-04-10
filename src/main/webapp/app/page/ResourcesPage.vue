<template>
  <div>
    <b-alert variant="danger" v-if="error">
      Ошибка
    </b-alert>

    <div class="row row-cols-3">
      <div v-for="resource in resources" :key="resource.id">
        <resource-component v-bind:resource="resource">
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
