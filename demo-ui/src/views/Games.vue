<template>
  <div>
    <table>
      <thead>
        <th>ID</th>
        <th>Name</th>
        <th>Publisher</th>
      </thead>
      <tbody>
        <tr
          v-for="game in games"
          :key="game.id"
          @click.left="pushRoute('DetailGame', { id: game.id })"
          @click.right.prevent="deleteGame(game.id)"
        >
          <td>{{ game.id }}</td>
          <td>{{ game.name }}</td>
          <td>{{ game.publisher }}</td>
        </tr>
      </tbody>
    </table>
    <button class="button button1" type="submit" @click.prevent="getGames" value="update">Aktualisieren</button>
    <button class="button button1" type="submit" @click.prevent="pushRoute('AddGame')" value="update">Spiel hinzufügen</button>

    <div v-if="error" class="error">{{ error }}</div>
  </div>
</template>

<script>
import axios from 'axios';
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';

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

    const deleteGame = async (id) => {
      // eslint-disable-next-line no-restricted-globals
      const verification = confirm(`Willst du wirklich das Spiel mit der id ${id} löschen?`);
      if (!verification) {
        return;
      }

      await axios({
        url: `/api/games/${id}`,
        method: 'delete',
      });
      // Ich kann faul seinn und rufe die Liste einfach neu ab, hier kann man auch frontendseitig sich das herausschnippeln
      // getGames();

      games.value = games.value.filter((g) => g.id !== id);
    };

    const router = useRouter();
    function pushRoute(route, params) {
      router.push({ name: route, params });
    }
    onMounted(() => {
      getGames();
    });

    return {
      games,
      error,
      getGames,
      pushRoute,
      deleteGame,
    };
  },
};
</script>

<style>
.error {
  background-color: red;
}

table {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td,
th {
  border: 1px solid #ddd;
  padding: 8px;
}

tr {
  cursor: pointer;
}
tr:nth-child(even) {
  background-color: #f2f2f2;
}
tr:hover {
  background-color: #ddd;
}

th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: center;
  background-color: #04aa6d;
  color: white;
}

button {
  background-color: #4caf50; /* Green */
  border: none;
  color: white;
  padding: 16px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  transition-duration: 0.4s;
  cursor: pointer;
}

.button1 {
  background-color: white;
  color: black;
  border: 2px solid #4caf50;
}
.button1:hover {
  background-color: #4caf50;
  color: white;
}
</style>
