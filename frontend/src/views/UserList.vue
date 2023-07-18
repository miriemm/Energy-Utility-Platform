<template>
  <v-card>
    <v-card-title>
      Clients
      <v-spacer></v-spacer>
      <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
      ></v-text-field>
      <v-btn @click="addUser">Add Client</v-btn>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="users"

        :search="search"
    >
      <template v-slot:item.devices="{item}">
        <span v-for="device in item.devices" :key="device.id">
          {{device.id}},
        </span>
      </template>
      <template v-slot:item.actions="{ item }">
        <v-icon
            color="green"
            small
            class="mr-2"
            @click="editUser(item)"
        >
          mdi-pencil
        </v-icon>
        <v-icon
            color="red"
            small
            @click="deleteUser(item)"
        >
          mdi-delete
        </v-icon>
      </template>
    </v-data-table>
    <UserDialog
        v-bind:opened="dialogVisible"
        :user="selectedItem"
        @refresh="refreshList"
    ></UserDialog>
  </v-card>
</template>

<script>
import api from "../api";
import UserDialog from "../components/UserDialog";

let i = 0;
export default {
  name: "UserList",
  components: { UserDialog },
  data() {
    return {
      users: [],
      search: "",
      headers: [
        {text: "Id", value: "id"},
        {
          text: "Name",
          align: "start",
          sortable: true,
          value: "name",
        },
        { text: "Birth Date", value: "birthDate" },
        { text: "Address", value: "address" },
        { text: "Username", value: "username"},
        { text: "Role", value: "role"},
        { text: "Devices", value: "devices"},
        { text: "Actions", value: "actions"}
      ],
      dialogVisible: false,
      selectedItem: {},
    };
  },
  methods: {
    editUser(user) {
      this.selectedItem = user;
      this.dialogVisible = true;
    },
    async deleteUser(user) {
      await api.users.delete(user);
      this.message = "Delete of user successful!";
      await this.refreshList();

    },
    addUser() {
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedItem = {};
      this.users = await api.users.allPersons();
    },
  },
  created() {
    this.refreshList();
  },
  computed: {
    test_data() {
      var formatted = [];
      for (i = 0; i < this.devices.length; i++) {
        formatted.push(this.devices.id)
      }
      return formatted;
    }
  },
  mounted() {
    this.test_data();
  }
};
</script>

<style scoped></style>