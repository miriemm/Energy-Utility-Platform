<template>
  <v-app>
    <v-app-bar flat max-height="65">
      <Header />
    </v-app-bar>


    <router-view></router-view>

    <v-footer>
      <Footer />
    </v-footer>

  </v-app>
</template>

<script>
import Header from "./components/common/Header";
import Footer from "./components/common/Footer";

export default {
  name: "App",

  components: {
    Header,
    Footer,
  },

  data: () => ({
    //
  }),
  computed: {
    isLoggedIn: function () {
      return this.$store.state.auth.status.loggedIn;
    },
    isAdminLogged: function() {
      return this.$store.getters["auth/isAdmin"];
    }
  },
};
</script>
