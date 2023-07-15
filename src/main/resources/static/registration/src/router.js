import { createWebHistory, createRouter } from "vue-router";

const routes =  [
  {
    path: "/",
    alias: "/people",
    name: "people",
    component: () => import("./components/PeopleList.vue")
  },
  {
    path: "/people/:id",
    name: "person-details",
    component: () => import("./components/Person")
  },
  {
    path: "/add",
    name: "add",
    component: () => import("./components/AddPerson")
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;