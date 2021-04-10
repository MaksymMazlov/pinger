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
        <a href="#" v-on:click="removeResource()" class="btn btn-danger">Видалить</a>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Prop, Vue} from 'vue-property-decorator';
import {IAccountResource} from "../../model/AccountResource";

@Component
export default class ResourceComponent extends Vue {
  @Prop()
  public resource: IAccountResource;

  public removeResource(): void {
    console.error("not implemented")
  }
}
</script>

<style scoped>

</style>