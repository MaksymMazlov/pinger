<template>
  <div id="app">
    <navbar></navbar>
    <div class="container">
      <h1>Hello World</h1>
    </div>
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
