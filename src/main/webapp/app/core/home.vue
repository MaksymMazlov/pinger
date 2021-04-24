<template>
  <div>
    <div class="row py-4">
      <div class="col-md-4 text-center">
        <h3>Моніторінг ресурсів</h3>
        <font-awesome-icon icon="chart-bar" size="8x"/>
        <p>Додавайте ресурси та оримуйте інформацію про їх стан та доступність</p>
      </div>
      <div class="col-md-4 text-center">
        <h3>Продвинута статистика</h3>
        <font-awesome-icon icon="clipboard-list" size="8x"/>
        <p>Отримуйте детальну інформацію про кожен випадок ресурсу</p>
      </div>
      <div class="col-md-4 text-center">
        <h3>Завжди на зв"язку</h3>
        <font-awesome-icon icon="comment-dots" size="8x"/>
        <p>
          Отримуйте інфомрацію про стан Вашого ресурсу прямо на телефон, або E-Mail.
        </p>
      </div>
    </div>
    <hr>
    <div class="row py-4">
      <div class="col-md-4">
        <h3>Моніторінг ресурсів</h3>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore
          magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
          consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
          pariatur.</p>
      </div>
      <div class="col-md-4">
        <h3>Продвинута статистика</h3>
        <p>
          Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore
          magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
          consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
          pariatur.
        </p>
      </div>
      <div class="col-md-4">
        <h3>Завжди на зв"язку</h3>
        <p>
          Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore
          magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
          consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
          pariatur.
        </p>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import Component from "vue-class-component";
import {Inject} from "vue-property-decorator";
import AccountService from "../service/AccountService";

@Component
export default class Home {
  @Inject('accountService')
  private accountService: () => AccountService;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.init();
    });
  }

  public init(): void {
    this.accountService().retrieveAccount();
  }
}
</script>