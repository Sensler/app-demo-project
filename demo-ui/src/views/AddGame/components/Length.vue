<template>
  <div :class="{ dark: inverted, block: true }" @click.left="clickIt">Länge: {{ value }}</div>
</template>

<script>
export default {
  name: 'Length',
  props: { inverted: { type: Boolean, required: false, default: false }, value: { type: String, required: true } },
  setup(props, context) {
    console.log(props);
    const clickIt = () => {
      context.emit('length-clicked');
    };
    return { clickIt };
  },
};
</script>

<style>
.dark {
  background-color: black;
  color: whitesmoke;
}
.block {
  margin: 5px;
  padding: 5px;
}
</style>
