<template>
  <div class="row">
    <div class="col-md-6 offset-md-3 col-sm-10 offset-sm-1">
      <h3>Реєстрація користувача</h3>

      <div v-if="error" class="alert alert-warning">
        Виникла помилка
      </div>

      <div v-if="success" class="alert alert-success">
        Користувача створено
      </div>

      <form v-if="!success" class="my-2" v-on:submit.prevent="createAccount()">
        <label>Email:</label>
        <input type="email" class="form-control" v-model="username">
        <label>Password:</label>
        <input type="password" class="form-control" v-model="password">
        <button type="submit" class="btn btn-success my-4">Реєстрація</button>
      </form>
    </div>
  </div>
</template>
<script lang="ts">
import Component from "vue-class-component";
import {Vue} from 'vue-property-decorator';
import axios from 'axios'

@Component
export default class Registration extends Vue {
  public username: string = null;
  public password: string = null;
  public success: boolean = false;
  public error: boolean = false;

  public createAccount(): void {
    this.error = false;
    axios.post('/api/account', {
      'email': this.username,
      'password': this.password
    }).then(result => {
      if (result && result.data.id) {
        this.success = true;
      }
    }).catch(err => {
      this.error = true;
    })
  }
}
</script>