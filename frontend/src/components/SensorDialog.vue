<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create sensor" : "Edit sensor" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="sensor.description" label="Description"/>
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
  name: "SensorDialog",
  props: {
    sensor: Object,
    opened: Boolean,
  },
  methods: {
    onHidden(){
      this.opened = false;
    },
    persist() {
      if (this.isNew) {
        api.sensors
            .create({
              description: this.sensor.description,
              maxValue: this.sensor.maxValue,

            })
            .then(() => this.$emit("refresh"));
      } else {
        api.sensors
            .edit({
              id: this.sensor.id,
              description: this.sensor.description,
              maxValue: this.sensor.maxValue,
            })
            .then(() => this.$emit("refresh"));
      }
    },
  },
  computed: {
    isNew: function () {
      return !this.sensor.id;
    },
  },
};
</script>

<style scoped></style>
