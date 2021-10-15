<template>
  <div>
    <div>Status:</div>
    <div class="status">{{ status }}</div>
  </div>
</template>

<script>
export default {
  setup(props) {
    console.log(props);
    const status = 'UP';
    return { props, status };
  },
};
</script>

<style>
.status {
  color: rebeccapurple;
}
</style>
