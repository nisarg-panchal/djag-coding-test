<template>
  <div class="submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="name">Name</label>
        <input
            type="text"
            class="form-control"
            id="name"
            required
            v-model="person.name"
            name="Name"
        />
      </div>

      <div class="form-group">
        <label for="email">Email</label>
        <input
            type="text"
            class="form-control"
            id="email"
            required
            v-model="person.email"
            name="Email"
        />
      </div>

      <div class="form-group">
        <label for="gender">Gender</label>
        <select v-model="person.gender">
          <option disabled value="">Please select one</option>
          <option>Male</option>
          <option>Female</option>
        </select>
      </div>

      <div class="form-group">
        <label for="age">Age</label>
        <input
            type="text"
            class="form-control"
            id="age"
            required
            v-model="person.age"
            name="age"
        />
      </div>

      <button @click="savePerson" class="btn btn-success">Submit</button>
    </div>

    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newPerson">Add</button>
    </div>
  </div>
</template>

<script>
import peopleDataService from "../services/PeopleDataService";

export default {
  name: "add-person",
  data() {
    return {
      person: {
        id: null,
        name: "",
        email: "",
        gender: "",
        age: 0
      },
      submitted: false
    };
  },
  methods: {
    savePerson() {
      let data = {
        name: this.person.name,
        email: this.person.email,
        gender: this.person.gender,
        age: this.person.age,
      };

      peopleDataService.create(data)
      .then(response => {
        this.person.id = response.data.id;
        console.log(response.data);
        this.submitted = true;
      })
      .catch(e => {
        console.log(e);
      });
    },

    newPerson() {
      this.submitted = false;
      this.person = {};
    }
  }
};
</script>

<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
</style>