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
      <v-btn @click="addDevice">Add Device</v-btn>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="devices"
        :search="search"
    >
      <template v-slot:item.actions="{ item }">
        <v-icon
            color="green"
            small
            class="mr-2"
            @click="editDevice(item)"
        >
          mdi-pencil
        </v-icon>
        <v-icon
            color="red"
            small
            @click="deleteDevice(item)"
        >
          mdi-delete
        </v-icon>
      </template>
    </v-data-table>
    <DeviceDialog
        v-bind:opened="dialogVisible"
        :device="selectedItem"
        @refresh="refreshList"
    ></DeviceDialog>
  </v-card>
</template>

<script>
import api from "../api";
import DeviceDialog from "../components/DeviceDialog";

export default {
  name: "DevicesList",
  components: { DeviceDialog },
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
        { text: "Sensor", value: "sensor.id"},
        { text: "Actions", value: "actions"}
      ],
      dialogVisible: false,
      selectedItem: {},
    };
  },
  methods: {
    editDevice(device) {
      this.selectedItem = device;
      this.dialogVisible = true;
    },
    async deleteDevice(device) {
      await api.device.delete(device);
      this.message = "Delete of device successful!";
      await this.refreshList();

    },
    addDevice() {
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedItem = {};
      this.devices = await api.device.allDevices();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>