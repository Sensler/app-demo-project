<template>
  <div>
    <table>
      <thead>
        <th>ID</th>
        <th>Name</th>
        <th>Publisher</th>
      </thead>
      <tbody>
        <tr v-for="game in games" :key="game.id">
          <td>{{ game.id }}</td>
          <td>{{ game.name }}</td>
          <td>{{ game.publisher }}</td>
        </tr>
      </tbody>
    </table>
    <button type="submit" @click.prevent="getGames" value="update">Aktualisieren</button>
    <div class="error">{{ error }}</div>
  </div>
</template>

<script>
import axios from 'axios';
import { ref, onMounted } from 'vue';

export default {
  name: 'Games',
  setup() {
    const games = ref(null);
    const error = ref(null);
    const getGames = async () => {
      try {
        const response = await axios({
          url: '/api/games',
          method: 'get',
        });
        games.value = response.data;
      } catch (e) {
        console.log(e);
        error.value = e.message;
      }
    };
    onMounted(() => {
      getGames();
    });

    return { games, error, getGames };
  },
};
</script>

<style>
.error {
  background-color: red;
}
</style>
