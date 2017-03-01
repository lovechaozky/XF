// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import 'element-ui/lib/theme-default/index.css';
import { sync } from 'vuex-router-sync';
import ElementUI from 'element-ui';
import 'normalize.css';
import VueResource from 'vue-resource';
import Vuex from 'vuex';
import Vue from 'vue';
import App from './App';
import router from './router';
import Store from './store';

Vue.use(ElementUI);
Vue.use(VueResource);
Vue.use(Vuex);

const store = new Vuex.Store(Store);
sync(store, router);

Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App },
});
