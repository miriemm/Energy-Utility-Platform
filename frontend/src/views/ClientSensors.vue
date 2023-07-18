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
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="sensors"
        item-key="id"
        :search="search"
        :single-expand="true"
        :expanded.sync="expandedSensor"
        show-expand
        @item-expanded="fetchMonitoredValues"
        class="elevation-1"
    >
    <template v-slot:expanded-item="{ headers, item }">
      <td :colspan="headers.length">
<!--        More info about {{ monitoredValues }}-->
        <v-data-table
          :headers="monitoredValuesHeaders"
          :items="monitoredValues"
          :sort-by="sortBy"
          :sort-desc="true"
        >
        </v-data-table>
      </td>
    </template>
    </v-data-table>
  </v-card>
</template>

<script>
import api from "../api";

export default {
  name: "SensorList",
  components: {},
  data() {
    return {
      sensors: [],
      monitoredValues: [],
      expandedSensor: [],
      search: "",
      sortBy: 'dateTime',
      headers: [
        { text: "Id", value: "id"},
        { text: "Description", value: "description" },
        { text: "Maximum value", value: "maxValue"},
        { text: "", value: 'data-table-expand'}
      ],
      monitoredValuesHeaders: [
        { text: "Id", value: "id"},
        { text: "DateTime", value: "dateTime"},
        { text: "Value", value: "value"}
      ],
      dialogVisible: false,
      selectedItem: {},
    };
  },
  methods: {
    async refreshList() {
      this.dialogVisible = false;
      this.selectedItem = {};
      this.sensors = await api.sensors.allPersonSensors();
    },
    async fetchMonitoredValues(expandedRow){
      var selectedSensor = expandedRow.item;
      this.monitoredValues = await api.monitoredValues.findAllMonitoredValuesBySensor(selectedSensor.id);
   },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>