<template>
  <v-content>
    <div class="staticHero">
      <v-img
          max-height="800"
          max-width="1000"
          src="../assets/images/energy.jpg">
        <v-row align="end" class="lightbox white--text pa-2 fill-height">
        </v-row>
      </v-img>
    </div>

    <v-toolbar-title class="titleAbout">Online platform</v-toolbar-title>

    <div>
      <v-container>
        <p>This platform is designed to manage clients, smart devices, sensors and their monitored values.
          The system can be accessed by two types of users after a login process: administrator, and clients.
          The  administrator can perform CRUD operations on client accounts (defined by ID, name, birth date, address) registered smart devices that have sensors attach to monitor
          energy consumption  (defined by ID, description, address (location), maximum energy consumption, average/baseline energy consumption),
          smart sensors attached to devices (ID, sensor description, maximum value) and on the mapping of clients to devices
          (each client can own one or more devices that are monitored) and sensors to devices (each device has one sensor monitoring its energy
          consumption).
        </p>
      </v-container>
    </div>
  </v-content>
</template>

<script>
export default {
  name: "About",
  data() {
    return {
      items: [
      ]
    };
  }
};
</script>