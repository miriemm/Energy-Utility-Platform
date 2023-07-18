import Vue from "vue";
import VueRouter from "vue-router";
import { auth as store } from "../store/auth.module";
import Login from "../views/Login";
import UserList from "@/views/UserList";
import Home from "@/views/Home";
import SensorList from "@/views/SensorList";
import DevicesList from "@/views/DevicesList";
import ClientSensors from "@/views/ClientSensors";
import ClientDevices from "@/views/ClientDevices";

Vue.use(VueRouter);

const routes = [
  { path: '/',
    name: "Home",
    component: Home },

  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.Fteam
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue"),
  },
  {
    path: "/users",
    name: "Users",
    component: UserList,
    beforeEnter: (to, from, next) => {
      if (store.getters.isAdmin) {
        next();
      } else {
        next({ name: "" });
      }
    },
  },
  {
    path: "/sensors",
    name: "Sensors",
    component: SensorList,
    beforeEnter: (to, from, next) => {
      if (store.getters.isAdmin) {
        next();
      } else {
        next({ name: "" });
      }
    },
  },
  {
    path: "/device",
    name: "Devices",
    component: DevicesList,
    beforeEnter: (to, from, next) => {
      if (store.getters.isAdmin) {
        next();
      } else {
        next({ name: "" });
      }
    },
  },
  {
    path: "/clientSensors",
    name: "ClientSensors",
    component: ClientSensors,
    beforeEnter: (to, from, next) => {
      if (store.getters.isClient) {
        next();
      } else {
        next({ name: "" });
      }
    },
  },
  {
    path: "/clientDevices",
    name: "clientDevices",
    component: ClientDevices,
    beforeEnter: (to, from, next) => {
      if (store.getters.isClient) {
        next();
      } else {
        next({ name: "" });
      }
    },
  },


];

const router = new VueRouter({
  routes,
});

export default router;
