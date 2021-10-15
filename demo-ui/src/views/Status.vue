<template>
  <div>
    <div>Status:</div>
    <div class="status">{{ status }}</div>
    <div>So oft wurde schon der Status abgerufen: {{ calls }}</div>
    <button type="submit" @click="updateStatus" value="Status update" name="Status update">
      Status update
    </button>
  </div>
</template>

<script>
import { ref } from 'vue';
import axios from 'axios';

export default {
  setup() {
    const status = ref('läuft');
    const calls = ref(0);

    function updateStatus() {
      axios
        .get('/actuator/health')
        .then((response) => {
          status.value = response.data.status;
        })

        .catch((e) => (status.value = `Pfeife, Server läuft nicht: ${e}`))
        .finally(() => (calls.value += 1));
    }
    return { status, calls, updateStatus };
  },
};
</script>

<style>
.status {
  color: rebeccapurple;
}
</style>
