<template>
    <div class ="Header">
 <hr>
      <p>Self-add packages</p>
      <cube-switch v-model="selfAdd">
  Switch
</cube-switch>

      <textarea
      id="story"
      name="story"
      rows="5"
      cols="100"
      onscroll="this.rows++;"
      v-if="selfAdd==true"
      v-model="value">
      </textarea>
    </div>
</template>


<script>
import latexCompileApi from "@/api/getData.js";

export default {
  name: "Header",
  component: {},
  data() {
    return { 
      selfAdd: false,
      value:"",
       data:""
       };
  },
  // 这是新增的方法
  methods: {
    send: function () {
      let param = {};
      param.fileContends = this.value;
      this.$axios({
        url: "http://localhost:8080/api/v1/pub/window/latexOneCompile",
        method: "post",
        data: param,
      }).then((res) => {
        //console.log("已经执行");
        //console.log(res);
        this.data = res.data
      });
    },
    add(){
      this.counter.push({});
    }

    
  },

  computed: {
    count: function () {
      return this.value.length;
    },
  },
};
</script>