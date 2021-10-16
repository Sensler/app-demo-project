<template>
  <div>
    <form>
      <h2>Spiel hinzufügen</h2>
      <div>
        <label for="name">Name: </label>
        <input type="text" name="name" id="name" />
      </div>
      <div>
        <label for="publisher">Publisher: </label>
        <input type="text" name="publisher" id="publisher" />
      </div>
      <div>
        <label for="buyingYear">Kaufdatum: </label>
        <input type="text" name="buyingYear" id="buyingYear" />
      </div>
    </form>
  </div>
</template>

<script>
export default {
  name: 'AddGame',
};
</script>

<style>
div {
  padding: 2px;
}
</style>
