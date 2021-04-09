<template>
  <div>
    <div v-if="error" class="aller aller-danger">
      Ошибка
    </div>

    <div class="row py-4">
      <div v-for="resource in resources" :key="resource.id">
        <div class="col">
          <div class="card m-1">
            <div class="card-header">{{resource.name}}</div>
            <div class="card-body">{{resource.host}}</div>
          </div>
        </div>
      </div>

    </div>
  </div>
</template>
<script lang="ts">
import Component from 'vue-class-component';
import {Inject, Vue} from 'vue-property-decorator';
import AccountResourceService from "../service/AccountResourceService";
import {IAccountResource} from "../model/AccountResource";

@Component
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
