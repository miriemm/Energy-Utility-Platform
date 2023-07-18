<template>
  <v-card>
    <v-card-title>
      Sensors
      <v-spacer></v-spacer>
      <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
      ></v-text-field>
      <v-btn @click="addSensor">Add Sensor</v-btn>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="sensors"
        :search="search"
    >
      <template v-slot:item.actions="{ item }">
        <v-icon
            color="green"
            small
            class="mr-2"
            @click="editSensor(item)"
        >
          mdi-pencil
        </v-icon>
        <v-icon
            color="red"
            small
            @click="deleteSensor(item)"
        >
          mdi-delete
        </v-icon>
      </template>
    </v-data-table>
    <SensorDialog
        v-bind:opened="dialogVisible"
        :sensor="selectedItem"
        @refresh="refreshList"
    ></SensorDialog>
  </v-card>
</template>

<script>
import api from "../api";
import SensorDialog from "../components/SensorDialog";

export default {
  name: "SensorList",
  components: { SensorDialog },
  data() {
    return {
      sensors: [],
      search: "",
      headers: [
        { text: "Id", value: "id"},
        { text: "Description", value: "description" },
        { text: "Maximum value", value: "maxValue"},
        { text: "Actions", value: "actions"}
      ],
      dialogVisible: false,
      selectedItem: {},
    };
  },
  methods: {
    editSensor(sensor) {
      this.selectedItem = sensor;
      this.dialogVisible = true;
    },
    async deleteSensor(sensor) {
      await api.sensors.delete(sensor);
      this.message = "Delete of sensor successful!";
      await this.refreshList();

    },
    addSensor() {
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedItem = {};
      this.sensors = await api.sensors.allSensors();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>