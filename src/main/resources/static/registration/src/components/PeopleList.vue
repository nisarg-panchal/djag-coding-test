<template>
  <div class="list row">
    <div class="col-md-6">
      <h4>List of People</h4>
      <ul class="list-group">
        <li class="list-group-item"
            :class="{ active: index == currentIndex }"
            v-for="(person, index) in people"
            :key="index"
            @click="setActivePerson(person, index)"
        >
          {{ person.name }}
        </li>
      </ul>
    </div>
    <div class="col-md-6">
      <div v-if="currentPerson">
        <h4>Person</h4>
        <div>
          <label><strong>Name:</strong></label> {{ currentPerson.name }}
        </div>
        <div>
          <label><strong>Email:</strong></label> {{ currentPerson.email }}
        </div>
        <div>
          <label><strong>Gender:</strong></label> {{ currentPerson.gender }}
        </div>
        <div>
          <label><strong>Age:</strong></label> {{ currentPerson.age }}
        </div>
        <router-link :to="'/people/' + currentPerson.id" class="badge badge-warning btn-done">Edit</router-link>
      </div>
      <div v-else>
        <br />
        <p>Please click on a Person...</p>
      </div>
    </div>
  </div>
</template>

<script>
import PersonDataService from "../services/PeopleDataService";

export default {
  name: "people-list",
  data() {
    return {
      people: [],
      currentPerson: null,
      currentIndex: -1,
      name: ""
    };
  },
  methods: {
    retrievePeople() {
      PersonDataService.getAll()
      .then(response => {
        this.people = response.data;
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
    },

    refreshList() {
      this.retrievePeople();
      this.currentPerson = null;
      this.currentIndex = -1;
    },

    setActivePerson(person, index) {
      this.currentPerson = person;
      this.currentIndex = person ? index : -1;
    },

    removeAllPersons() {
      PersonDataService.deleteAll()
      .then(response => {
        console.log(response.data);
        this.refreshList();
      })
      .catch(e => {
        console.log(e);
      });
    },

    searchName() {
      PersonDataService.findByName(this.title)
      .then(response => {
        this.persons = response.data;
        this.setActivePerson(null);
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
    }
  },
  mounted() {
    this.retrievePeople();
  }
};
</script>

<style>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
</style>