<template>
  <div>
    <form>
      <h2>Spiel hinzufügen</h2>
      <div>
        <label for="name">Name: </label>
        <input type="text" name="name" id="name" v-model.lazy="inputState.name" />
        <length inverted="true" :value="nameLength" @length-clicked="logger('geklickt')" />
      </div>
      <div>
        <label for="publisher">Publisher: </label>
        <input type="text" name="publisher" id="publisher" v-model="inputState.publisher" />

        <length :value="inputState.publisher.length" />
      </div>
      <div>
        <label for="buyingYear">Kaufdatum: </label>
        <input type="text" name="buyingYear" id="buyingYear" v-model="inputState.buyingYear" />
      </div>
      <button type="submit" value="add" name="add" @click="addGame">Push it</button>
    </form>
  </div>
</template>

<script>
import { computed, reactive, watch } from 'vue';
import axios from 'axios';
import { onBeforeRouteLeave, useRouter } from 'vue-router';
import Length from './components/Length.vue';

export default {
  components: { Length },
  name: 'AddGame',

  setup() {
    const inputState = reactive({
      name: '',
      publisher: '',
      buyingYear: 2021,
    });
    const nameLength = computed(() => inputState.name.length);
    const logger = (params) => {
      console.log(params);
    };
    watch(inputState, (value, oldValue) => {
      console.log(`value=${value.name}, oldValue=${oldValue.name}`);
    });
    let added = false;
    const router = useRouter();
    const addGame = async () => {
      axios({
        url: 'api/games',
        method: 'post',
        data: inputState,
      });
      added = true;

      router.push({ name: 'Games' });
    };

    onBeforeRouteLeave(() => {
      console.log('wow hier lande ich ja immer');
      // eslint-disable-next-line no-restricted-globals
      return added ? true : confirm('Willst du wirklich kein Spiel hinzufügen?');
    });
    return {
      inputState,
      nameLength,
      addGame,
      logger,
    };
  },
};
</script>

<style>
div {
  padding: 2px;
}
</style>
