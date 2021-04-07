<template>
  <div id="app">
    <div class="wrapper">
      <div id="app-header">
        <navbar></navbar>
      </div>
      <div class="container">
        <router-view></router-view>
      </div>
    </div>
    <footer class="footer bg-secondary text-light">
      <div class="container text-center">
        Pinger.ua
      </div>
    </footer>
  </div>
</template>

<script lang="ts">
import Vue from 'vue';
import Component from 'vue-class-component';
import Navbar from './core/navbar.vue';

@Component({
  components:{
    'navbar': Navbar,
  }
})
export default class App extends Vue {

}
</script>
<style scoped>
.footer {
  width: 100%;
  position: absolute;
  bottom: 0;
  height: 50px;
  line-height: 50px;
}
</style>