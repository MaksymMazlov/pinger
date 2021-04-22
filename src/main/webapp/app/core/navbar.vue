<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
      <router-link class="navbar-brand" to="/">Pinger</router-link>
      <button class="navbar-toggler" type="button"
              data-bs-toggle="collapse"
              data-bs-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent"
              aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto">
          <li class="nav-item">
            <router-link class="nav-link active" to="/"><font-awesome-icon icon="home"/> Головна</router-link>
          </li>
          <li v-if="!authenticated" class="nav-item">
            <router-link class="nav-link" to="/page/account/login"><font-awesome-icon icon="user"/> Авторизація</router-link>
          </li>
          <li v-if="!authenticated" class="nav-item">
            <router-link class="nav-link" to="/page/account/registration"><font-awesome-icon icon="plus"/> Реєстрація</router-link>
          </li>
          <li v-if="authenticated" class="nav-item">
            <router-link class="nav-link" to="/page/resource"><font-awesome-icon icon="list"/> Мої Ресурси</router-link>
          </li>
        </ul>

        <ul class="navbar-nav ml-auto">
          <li v-if="authenticated" class="nav-item">
            <a href="#" class="nav-link"><font-awesome-icon icon="user"/> Профіль</a>
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