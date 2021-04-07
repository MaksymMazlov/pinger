<template>
  <div class="row">
    <div class="col-md-6 offset-md-3 col-sm-10 offset-sm-1">
      <h3>Авторизація</h3>
      <form class="my-2" action="/login" method="post">
        <label>Email:</label>
        <input type="email" name="username" class="form-control">
        <label>Password:</label>
        <input type="password" name="password" class="form-control">
        <button class="btn btn-success my-4">Login</button>
      </form>
    </div>
  </div>
</template>
<script lang="ts">
import Component from "vue-class-component";

@Component
export default class Login {

}
</script>x