<template>

<div id="wrap">
  <router-view></router-view>
  
</div>

</template>


<script>
export default {
  name: "wrap",
};

</script>




<style lang="scss">
#wrap {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 130px;
}

#nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}

#wrap{
  width: 100%;
  height: 100%;
  background-color: white;

}

</style>
