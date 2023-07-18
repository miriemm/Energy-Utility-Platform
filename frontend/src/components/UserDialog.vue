<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create client" : "Edit client" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="user.name" label="Name" />
          <v-text-field v-model="user.birthDate" label="Birth Date" />
          <v-text-field v-model="user.address" label="Address" />
          <v-text-field v-model="user.username" label="Username" />
          <v-text-field v-model="user.password" label="Password" />
          <template>
            <v-container fluid>
              <div v-for="item in items" :key="item">
                <input type="checkbox"
                       v-model = "selected"
                       :value="item"
                >
                {{item.id}}
              </div>
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
  name: "UserDialog",
  props: {
    user: Object,
    opened: Boolean,
  },
  data: () => ({
    items: [],
    selected : []
  })
  ,
  methods: {
    onHidden(){
      this.opened = false;
    },
    persist() {
      if (this.isNew) {
        api.users
            .create({
              name: this.user.name,
              birthDate: this.user.birthDate,
              address: this.user.address,
              username: this.user.username,
              password: this.user.password,
              devices: this.selected
            })
            .then(() => this.$emit("refresh"));
      } else {
        api.users
            .edit({
              id: this.user.id,
              name: this.user.name,
              birthDate: this.user.birthDate,
              address: this.user.address,
              username: this.user.username,
              password: this.user.password,
              devices: this.selected
            })
            .then(() => this.$emit("refresh"));
      }
    },
    loadDevices() {
      api.device.allDevices().then((response) => {
        this.items = response;
      });

      if (!this.user.id){
        this.user.devices = this.items[0];
      }
    },
  },
  mounted() {
    this.loadDevices();
  },
  computed: {
    isNew: function () {
      return !this.user.id;
    },
  },
};
</script>

<style scoped></style>
