<template>
  <div class="col">
    <div class="card my-1">
      <div class="card-body">
        <div v-if="error" class="alert alert-danger">Ошибка... {{ error }}</div>
        <h5 class="card-title">{{ resource.name }}
          <span v-if="resource.status === 'ACTIVE'" class="badge badge-pill badge-primary float-right">Активно</span>
          <span v-if="resource.status === 'SUSPENDED'" class="badge badge-pill badge-warning float-right">Призупинено</span>
          <span v-if="resource.status === 'ARCHIVED'" class="badge badge-pill badge-secondary float-right">Архівовано</span>
        </h5>
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
        <button class="btn btn-sm btn-primary">
          <font-awesome-icon icon="question-circle"/>
          Детальніше
        </button>

        <button v-on:click="pauseResource()" v-if="resource.status === 'ACTIVE'" class="btn btn-sm btn-warning">
          <font-awesome-icon icon="pause"/>
          Призупинити
        </button>

        <button v-on:click="resumeResource()" v-if="resource.status === 'SUSPENDED'" class="btn btn-sm btn-success">
          <font-awesome-icon icon="play"/>
          Поновити
        </button>
        <button v-on:click="archiveResource()" v-if="resource.status === 'SUSPENDED'" class="btn btn-sm btn-danger">
          <font-awesome-icon icon="archive"/>
          Архівувати
        </button>

        <button v-on:click="removeResource()" v-if="resource.status === 'ARCHIVED'" class="btn btn-sm btn-danger">
          <font-awesome-icon icon="trash"/>
          Видалити
        </button>
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
  public error: string = null;

  public pauseResource(): void {
    axios.patch(`/api/resource/${this.resource.id}`, {
      'status': 'SUSPENDED'
    }).then(r => {
      this.resource.status = 'SUSPENDED';
    }).catch(err => this.error = err);
  }

  public resumeResource(): void {
    axios.patch(`/api/resource/${this.resource.id}`, {
      'status': 'ACTIVE'
    }).then(r => {
      this.resource.status = 'ACTIVE';
    }).catch(err => this.error = err);
  }

  public archiveResource(): void {
    axios.patch(`/api/resource/${this.resource.id}`, {
      'status': 'ARCHIVED'
    }).then(r => {
      this.resource.status = 'ARCHIVED';
    }).catch(err => this.error = err);
  }

  public removeResource(): void {
    axios.delete(`/api/resource/${this.resource.id}`)
        .then(r => {
          const index = this.resources.indexOf(this.resource, 0);
          if (index > -1) {
            this.resources.splice(index, 1);
          }
        }).catch(err => this.error = err);
  }
}
</script>

<style scoped>

</style>