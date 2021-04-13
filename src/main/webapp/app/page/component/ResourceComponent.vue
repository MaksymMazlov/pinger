<template>
  <div class="col">
    <div class="card my-1">
      <div class="card-body">
        <h5 class="card-title">{{ resource.name }}</h5>
        <table class="table table-sm">
          <tr>
            <td>Хост</td>
            <td>{{ resource.host }}</td>
          </tr>
          <tr>
            <td>Тип</td>
            <td>{{ resource.type }}</td>
          </tr>
          <tr>
            <td>Створено</td>
            <td>{{ resource.created }}</td>
          </tr>
        </table>
        <button v-on:click="removeResource()" class="btn btn-danger">Видалить</button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Prop, Vue} from 'vue-property-decorator';
import {IAccountResource} from "../../model/AccountResource";
import axios from 'axios';

@Component
export default class ResourceComponent extends Vue {
  @Prop()
  public resource: IAccountResource
  @Prop()
  public resources: IAccountResource[];

  public removeResource(): void {
    axios.delete(`/api/resource/${this.resource.id}`)
        .then(r => {
          console.log('aaaaaaaaa');
          const index = this.resources.indexOf(this.resource, 0);
          if (index > -1) {
            this.resources.splice(index, 1);
          }
        })
  }
}
</script>

<style scoped>

</style>