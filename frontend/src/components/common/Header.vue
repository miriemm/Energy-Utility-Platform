<template>
  <v-container>
    <v-toolbar flat class="mainHeader">
      <v-toolbar-title>Automated Home Life</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items class="hidden-xs-only">
        <v-btn v-if="!isAdminLogged && !isClientLogged" text><router-link to="/">Home</router-link></v-btn>
        <v-btn v-if="isAdminLogged" text><router-link to="/users">Users</router-link></v-btn>
        <v-btn v-if="!isAdminLogged && !isClientLogged" text><router-link to="/about">About</router-link></v-btn>
        <v-btn v-if="isAdminLogged" text><router-link to="/device">Devices</router-link></v-btn>
        <v-btn v-if="isAdminLogged" text><router-link to="/sensors">Sensors</router-link></v-btn>
        <v-btn v-if="isClientLogged" text><router-link to="/clientSensors">Client Sensors</router-link></v-btn>
        <v-btn v-if="isClientLogged" text><router-link to="/clientDevices">Client Devices</router-link></v-btn>
        <v-btn v-if="!isLoggedIn" text><router-link to="/login">Login</router-link></v-btn>
        <v-btn v-if="isLoggedIn" @click="logout" text><router-link to="/">Logout</router-link></v-btn>

      </v-toolbar-items>
      <div class="hidden-sm-and-up">
        <v-menu offset-y>
          <template v-slot:activator="{ on }">
            <v-app-bar-nav-icon v-on="on"></v-app-bar-nav-icon>
          </template>
          <v-list class="responsiveMenu">
            <v-list-item>
              <v-list-item-title><router-link to="/">Home</router-link></v-list-item-title>
            </v-list-item>
            <v-list-item>
              <v-list-item-title><router-link to="/users">Users</router-link></v-list-item-title>
            </v-list-item>
            <v-list-item>
              <v-list-item-title><router-link to="/about">About</router-link></v-list-item-title>
            </v-list-item>
            <v-list-item>
              <v-list-item-title><router-link to="/sensors">Sensors</router-link></v-list-item-title>
            </v-list-item>
            <v-list-item>
              <v-list-item-title><router-link to="/clientSensors">Client Sensors</router-link></v-list-item-title>
            </v-list-item>
            <v-list-item>
              <v-list-item-title><router-link to="/clientDevices">Client Devices</router-link></v-list-item-title>
            </v-list-item>
            <v-list-item>
              <v-list-item-title><router-link to="/login">Login</router-link></v-list-item-title>
            </v-list-item>
            <v-list-item>
              <v-list-item-title><router-link to="/">Logout</router-link></v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
      </div>
    </v-toolbar>
  </v-container>
</template>

<script>
export default {
  name: "Header",
  data: () => ({
  }),
  methods: {
    logout() {
      this.$store.dispatch("auth/logout");
    }
  },
  computed: {
    isLoggedIn: function () {
      return this.$store.state.auth.status.loggedIn;
    },
    isAdminLogged: function() {
      return this.$store.getters["auth/isAdmin"];
    },
    isClientLogged: function(){
      return this.$store.getters["auth/isClient"];
    }
  }
};
</script>