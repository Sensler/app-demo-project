<template>
  <div>
    <div>Status:</div>
    <div class="status">{{ status }}</div>
  </div>
</template>

<script>
export default {
  setup(props) {
    const status = 'läuft';
    return { props, status };
  },
};
</script>

<style>
.status {
  color: rebeccapurple;
}
</style>
