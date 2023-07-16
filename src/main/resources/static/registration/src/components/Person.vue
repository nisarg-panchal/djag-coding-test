<template>
  <div v-if="currentPerson" class="edit-form">
    <h4>Person</h4>
    <form>
      <div class="form-group">
        <label for="name">Name</label>
        <input type="text" class="form-control" id="name"
               v-model="currentPerson.name"
        />
      </div>
      <div class="form-group">
        <label for="email">Email</label>
        <input type="text" class="form-control" id="email"
               v-model="currentPerson.email"
        />
      </div>
      <div class="form-group">
        <label for="gender">Gender</label>
        <select v-model="currentPerson.gender">
          <option disabled value="">Please select one</option>
          <option>Male</option>
          <option>Female</option>
        </select>
      </div>
      <div class="form-group">
        <label for="age">Age</label>
        <input type="text" class="form-control" id="age"
               v-model="currentPerson.age"
        />
      </div>

    </form>

    <button class="badge badge-danger mr-2" @click="deletePerson">
      Delete
    </button>

    <button type="submit" class="badge badge-success" @click="updatePerson">
      Update
    </button>
    <p>{{ message }}</p>
  </div>

  <div v-else>
    <br />
    <p>Please click on a Person...</p>
  </div>
</template>

<script>
import PeopleDataService from "../services/PeopleDataService";

export default {
  name: "person-details",
  data() {
    return {
      currentPerson: {
        id: null,
        name: "",
        email: "",
        gender: "",
        age: 0
      },
      message: ''
    };
  },
  methods: {
    getPerson(id) {
      console.log(id);
      /*this.currentPerson.id = '200';
      this.currentPerson.name = 'test';
      this.currentPerson.email = 'test';
      this.currentPerson.age = 'test';
      this.currentPerson.gender = 'Female';*/

            PeopleDataService.get(id)
            .then(response => {
              console.log("Response:"+JSON.stringify(response));
              this.currentPerson.id = response.data.id;
              this.currentPerson.name = response.data.name;
              this.currentPerson.email = response.data.email;
              this.currentPerson.age = response.data.age;
              this.currentPerson.gender = response.data.gender;
            })
            .catch(e => {
              console.log(JSON.stringify(e));
            });
    },

    updatePerson() {
      PeopleDataService.update(this.currentPerson.id, this.currentPerson)
      .then(response => {
        console.log(response.data);
        this.message = 'The person was updated successfully!';
      })
      .catch(e => {
        console.log(e);
      });
    },

    deletePerson() {
      PeopleDataService.delete(this.currentPerson.id)
      .then(response => {
        console.log(response.data);
        this.$router.push({ name: "persons" });
      })
      .catch(e => {
        console.log(e);
      });
    }
  },
  mounted() {
    this.message = '';
    this.getPerson(this.$route.params.id);
  }
};
</script>

<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>