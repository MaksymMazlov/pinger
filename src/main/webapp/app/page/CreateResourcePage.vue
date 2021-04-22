<template>
  <div>
    <div class="alert alert-danger" v-if="error">
      Ошибка
    </div>

    <div class="row row-cols-2">
      <div class="col">
        <form class="my-2">
          <label>Навзва</label>
          <input class="form-control" type="text" v-model="resource.name">
          <div class="form-group">
            <label for="typeSelect">Тип</label>
            <select class="form-control" id="typeSelect" v-model="resource.type">
              <option>PING</option>
              <option>URL</option>
            </select>
          </div>
          <label>Хост</label>
          <input class="form-control" type="text" v-model="resource.host">
          <label>Інтервал</label>
          <input class="form-control" type="number" v-model="resource.monitoringInterval">
          <button type="button" class="btn btn-primary my-2" v-on:click="createResource">Створити</button>
        </form>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import Component from 'vue-class-component';
import {Vue} from 'vue-property-decorator';
import {AccountResource, IAccountResource} from "../model/AccountResource";
import axios from 'axios';

@Component
export default class CreateResourcePage extends Vue {
  public error: string = null;
  public resource: IAccountResource = null;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.init();
    });
  }

  public init(): void {
    this.resource = new AccountResource();
  }

  public createResource(): void {
    axios.post('/api/resource', this.resource).then(r=>{
      (<any>this).$router.go(-1);
    }).catch(err=>this.error=err);
  }
}
</script>
