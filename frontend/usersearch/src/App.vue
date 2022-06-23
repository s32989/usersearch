<template>
  <div id="app">
    <br />
    <div class="centerMe">
      <input
        type="text"
        name="test"
        id="testMe"
        placeholder="first name"
        v-model="firstName"
      />
      <div v-if="firstNameEmpty" class="centerMe">
        Please enter first name
      </div>
      <br />
      <input
        type="text"
        name="test"
        id="testMe1"
        placeholder="last name"
        v-model="lastName"
      />
      <div v-if="lastNameEmpty" class="centerMe">
        Please enter last name
      </div>
      <br />
      <input
        type="email"
        name="test"
        id="testMe2"
        placeholder="email"
        @click="clickInEmail"
        v-model="email"
      />
      <div v-if="!emailContainsAtDot" class="centerMe">
        Please enter proper email
      </div>
      <br />

      <button
        type="submit"
        :disabled="!emailContainsAtDot || firstNameEmpty || lastNameEmpty"
        @click="registerUser"
      >
        Register
      </button>
    </div>
    <br />

    <div v-if="responseBad" class="centerMe red">
      Email already exists
    </div>

    <div v-if="successRegister" class="centerMe green">
      Success!
    </div>
    <div class="centerMe">
      <br />
      <p class="centerMe">
        Search for users by first name, last name or email address
      </p>

      <br />
      <div class="flexMe">
        <input
          type="text"
          name="searchUser"
          id="searchUser"
          v-model="searchTerm"
        />
        <button type="submit" :disabled="searchTermEmpty" @click="searchUser">
          Search
        </button>
      </div>

      <table>
        <tr v-for="returnedUsers in returnedUsers" v-bind:key="returnedUsers">
          <td>first name: {{ returnedUsers.firstname }}</td>
          <td>last name: {{ returnedUsers.lastname }}</td>
          <td>last name: {{ returnedUsers.email }}</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "app",
  data() {
    return {
      firstName: "",
      lastName: "",
      email: "",
      responseCodeGood: true,
      successRegister: false,
      searchTerm: "",
      returnedUsers: []
    };
  },
  methods: {
    registerUser() {
      this.responseCodeGood = true;
      let result = axios
        .post(
          "http://localhost:8080/register",
          {
            firstName: this.firstName,
            lastName: this.lastName,
            email: this.email
          },
          {
            headers: {
              "Content-Type": "application/json"
            }
          }
        )
        .then(res => {
          this.responseCodeGood = res.status == 200;
          if (res.status == 200) {
            this.successRegister = true;
            (this.firstName = ""), (this.lastName = ""), (this.email = "");
          }
        })
        .catch(res => {
          this.responseCodeGood = res.status == 200;
        });
    },
    searchUser() {
      let result = axios
        .post(
          "http://localhost:8080/search",
          {
            searchTerm: this.searchTerm
          },
          {
            headers: {
              "Content-Type": "application/json"
            }
          }
        )
        .then(res => {
          this.returnedUsers = [...res.data];
          console.log(returnedUsers);
        })
        .catch(res => {});
    },
    clickInEmail() {
      this.responseCodeGood = true;
    }
  },
  computed: {
    emailContainsAtDot() {
      if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(this.email)) {
        return true;
      }
      return false;
    },
    firstNameEmpty() {
      return !this.firstName.length > 0;
    },
    lastNameEmpty() {
      return !this.lastName.length > 0;
    },
    responseBad() {
      return !this.responseCodeGood;
    },
    searchTermEmpty() {
      return !this.searchTerm.length > 0;
    }
  }
};
</script>

<style>
body {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
  background-color: cornsilk;
}
.centerMe {
  display: grid;
  align-items: center;
  justify-content: center;
}
.flexMe {
  display: flex;
  align-items: center;
  justify-content: center;
}
.red {
  color: crimson;
}
.green {
  color: green;
}
td {
  padding: 15px;
  border: 1px solid black;
}
</style>
