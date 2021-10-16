import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/Home.vue';
import Status from '../views/Status.vue';
import Games from '../views/Games.vue';
import AddGame from '../views/AddGame.vue';
import DetailGame from '../views/DetailGame.vue';

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
  {
    path: '/addGame',
    name: 'AddGame',
    component: AddGame,
  },
  {
    path: '/game/:id',
    name: 'DetailGame',
    component: DetailGame,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
