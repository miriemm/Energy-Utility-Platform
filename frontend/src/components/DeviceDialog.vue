<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create device" : "Edit device" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="device.description" label="Description"/>
          <v-text-field v-model="device.location" label="Location" />
          <v-text-field v-model="device.maxEnergyConsumption" label="Maximum Energy Consumption" />
          <v-text-field v-model="device.baseline" label="Baseline" />
          <template>
            <v-container fluid>
              <v-row align="center">
                <v-col
                    class="d-flex"
                    cols="12"
                    sm="6"
                >
                  <v-select
                      v-model="device.sensor"
                      :items="items"
                      item-text="id"
                      label="Sensor"
                      return-object
                      outlined
                  ></v-select>
                </v-col>
              </v-row>
            </v-container>
          </template>
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "DeviceDialog",
  props: {
    device: Object,
    opened: Boolean,
  },
  data: () => ({
    items: []
    })
  ,
  methods: {
    onHidden(){
      this.opened = false;
    },
    persist() {
      if (this.isNew) {
        api.device
            .create({
              description: this.device.description,
              location: this.device.location,
              maxEnergyConsumption: this.device.maxEnergyConsumption,
              baseline: this.device.baseline,
              sensor: this.device.sensor

            })
            .then(() => this.$emit("refresh"));
      } else {
        api.device
            .edit({
              id: this.device.id,
              description: this.device.description,
              location: this.device.location,
              maxEnergyConsumption: this.device.maxEnergyConsumption,
              baseline: this.device.baseline,
              sensor: this.device.sensor

            })
            .then(() => this.$emit("refresh"));
      }
    },
    loadSensors() {
      api.sensors.allSensors().then((response) => {
            //console.log(response);
            this.items = response;
          });

      if (!this.device.id){
        this.device.sensor = this.items[0];
      }
    }
  },
  mounted() {
    this.loadSensors();
  },
  computed: {
    isNew: function () {
      return !this.device.id;
    },
  },
};
</script>

<style scoped></style>
