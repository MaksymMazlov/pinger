<template>
  <div class="row">
    <div class="col-md-6 offset-md-3 col-sm-10 offset-sm-1">
      <h3>Авторизація</h3>
      <div v-if="authenticationError" class="alert alert-warning">
        Помилка авторизації
      </div>
      <form class="my-2" v-on:submit.prevent="doLogin">
        <label>Email:</label>
        <input type="email" v-model="login" class="form-control">
        <label>Password:</label>
        <input type="password" v-model="password" class="form-control">
        <button class="btn btn-success my-4">Login</button>
      </form>
    </div>
  </div>
</template>
<script lang="ts">
import Component from "vue-class-component";
import {Inject} from "vue-property-decorator";
import AccountService from "../service/AccountService";
import axios from "axios";

@Component
export default class Login {
  @Inject('accountService')
  private accountService: () => AccountService;
  public authenticationError = null;
  public login = null;
  public password = null;

  public doLogin(): void {
    const data = new FormData();
    data.append('username', this.login);
    data.append('password', this.password);

    axios
        .post('/api/login', data)
        .then(result => {
          // const bearerToken = result.headers.authorization;
          // if (bearerToken && bearerToken.slice(0, 7) === 'Bearer ') {
          //   const jwt = bearerToken.slice(7, bearerToken.length);
          //   if (this.rememberMe) {
          //     localStorage.setItem('jhi-authenticationToken', jwt);
          //   } else {
          //     sessionStorage.setItem('jhi-authenticationToken', jwt);
          //   }
          // }
          this.authenticationError = false;
          this.accountService().retrieveAccount();
        })
        .catch(() => {
          this.authenticationError = true;
        });
  }
}
</script>x