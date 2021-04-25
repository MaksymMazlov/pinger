<template>
  <div>
    <div class="row">
      <div class="col my-2" v-if="resource">
        <h3>{{ resource.name }}</h3>
        <span v-if="resource.status === 'ACTIVE'" class="badge badge-pill badge-primary">Активно</span>
        <span v-if="resource.status === 'SUSPENDED'" class="badge badge-pill badge-warning">Призупинено</span>
        <span v-if="resource.status === 'ARCHIVED'" class="badge badge-pill badge-secondary">Архівовано</span>
        <table class="table my-2">
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
          <tr>
            <td>Uptime</td>
            <td>
              <div class="progress">
                <div class="progress-bar"
                     v-bind:class="{'bg-warning':uptime < 95, 'bg-success': uptime >= 95}"
                     v-bind:style="{width: uptime + '%'}"
                     v-bind:aria-valuenow="uptime"
                     role="progressbar"
                     aria-valuemin="0"
                     aria-valuemax="100">{{ uptime }}%
                </div>
              </div>
            </td>
          </tr>
          <tr>
            <td>Статус</td>
            <td>
              <table border="2px" style="border-color: darkgreen" class="text-center">
                <tr v-for="week in weeklyStatus" :key="week.id">
                  <td v-if="week.isAvailable" class="bg-success text-white">
                    <font-awesome-icon icon="check-circle"/>
                  </td>

                  <td v-if="!week.isAvailable" class="bg-danger text-white">
                    <font-awesome-icon icon="check-circle"/>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
      </div>
    </div>
    <hr>
    <div class="row">
      <div class="col">

        <div v-for="event in events" :key="event.id">
          <div class="card border-danger my-2">
            <div class="card-body">
              <h5 class="card-title"><font-awesome-icon icon="exclamation-triangle"/> {{event.type}}</h5>
              <h6 class="card-subtitle mb-2 text-muted">{{event.dateTime}}</h6>
              {{event.reason}}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Component from 'vue-class-component';
import {Inject, Vue} from 'vue-property-decorator';
import AccountResourceService from '../service/AccountResourceService';
import {IAccountResource} from "../model/AccountResource";
import MonitoringEventService from "../service/MonitoringEventService";
import {IMonitoringEvent} from "../model/MonitoringEvent";
import {IMonitoringByWeek} from "../model/MonitoringByWeek";
import MonitoringByWeekService from "../service/MonitoringByWeekService";

@Component
export default class ResourceDetailsPage extends Vue {
  @Inject('accountResourceService')
  protected accountResourceService: () => AccountResourceService;

  @Inject('monitoringEventService')
  protected monitoringEventService: () => MonitoringEventService;

  @Inject('monitoringByWeekService')
  protected monitoringByWeekService: () => MonitoringByWeekService;

  private resource: IAccountResource = null;
  private uptime: number = null;
  private events: IMonitoringEvent[] = null;
  private weeklyStatus: IMonitoringByWeek[] = null;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.resourceId) {
        vm.init(to.params.resourceId);
      }
    });
  }

  public init(resourceId: number): void {
    this.accountResourceService().getResourceById(resourceId).then(r => this.resource = r);
    this.accountResourceService().getResourceUptime(resourceId).then(r => this.uptime = r);
    this.monitoringEventService().getEvents(resourceId).then(r => this.events = r);
    this.monitoringByWeekService().getWeeklyStatus(resourceId).then(r=>this.weeklyStatus = r);
  }
}
</script>

<style scoped>

</style>