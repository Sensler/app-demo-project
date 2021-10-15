module.exports = {
  devServer: {
    proxy: {
      '^/api': {
        target: 'http://localhost:8080',
        ws: true,
        changeOrigin: true,
      },
      '^/actuator': {
        target: 'http://localhost:8080',
        ws: true,
        changeOrigin: true,
      },
    },
  },
};
