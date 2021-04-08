<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
      <a class="navbar-brand" href="/">Pinger</a>
      <button class="navbar-toggler" type="button"
              data-bs-toggle="collapse"
              data-bs-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent"
              aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <router-link class="nav-link active" to="/">Головна</router-link>
          </li>
          <li v-if="!authenticated" class="nav-item">
            <router-link class="nav-link" to="/page/account/login">Авторизація</router-link>
          </li>
          <li v-if="!authenticated" class="nav-item">
            <router-link class="nav-link" to="/page/account/registration">Реєстрація</router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>
<script lang="ts">
import Vue from 'vue';
import Component from "vue-class-component";

@Component
export default class Navbar extends Vue {
  public get authenticated(): boolean {
    return this.$store.getters.authenticated;
  }
}
</script>