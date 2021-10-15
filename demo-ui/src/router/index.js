import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/Home.vue';
import Status from '../views/Status.vue';
import Games from '../views/Games.vue';

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue'),
  },
  {
    path: '/status',
    name: 'Status',
    component: Status,
  },
  {
    path: '/games',
    name: 'Games',
    component: Games,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
