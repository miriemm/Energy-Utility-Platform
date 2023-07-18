<template>
  <v-card>
    <v-card-title>
      Devices
      <v-spacer></v-spacer>
      <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
      ></v-text-field>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="devices"
        :search="search"
    >
    </v-data-table>
  </v-card>
</template>

<script>
import api from "../api";

export default {
  name: "ClientDevices",
  data() {
    return {
      devices: [],
      search: "",
      headers: [
        { text: "Id", value: "id"},
        { text: "Description", value: "description" },
        { text: "Location", value: "location"},
        { text: "Maximum energy consumption", value: "maxEnergyConsumption"},
        { text: "Baseline", value: "baseline"},
        { text: "Sensor", value: "sensor.id"}
      ],
      dialogVisible: false,
      selectedItem: {},
    };
  },
  methods: {
    async refreshList() {
      this.dialogVisible = false;
      this.selectedItem = {};
      this.devices = await api.device.allPersonDevices();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>