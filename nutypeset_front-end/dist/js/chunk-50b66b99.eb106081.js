(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-50b66b99"],{"57e3":function(e,t,s){},7101:function(e,t,s){"use strict";s.r(t);var a=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{attrs:{id:"wrap"}},[s("div",{staticClass:"nav"},[s("button",{staticClass:"b1",on:{click:e.goIndex}},[e._v("back")])]),s("div",{staticClass:"main"},[s("cube-form",{attrs:{model:e.model},on:{submit:e.submitHandler}},[s("cube-form-group",[s("cube-form-item",{attrs:{field:e.fields[0]}}),s("cube-form-item",{attrs:{field:e.fields[1]}})],1),s("cube-form-group",[s("button",{staticClass:"b3"},[e._v("Login")])])],1),s("router-link",{staticClass:"reg",attrs:{to:"/register"}},[e._v("Register")])],1)])},r=[],o=s("c1fb"),i={data:function(){return{model:{email:"",password:""},fields:[{type:"input",modelKey:"email",label:"email",props:{placeholder:"Please Enter Email"},rules:{required:!0},messages:{required:"Email Cannot Be Empty!"}},{type:"input",modelKey:"password",label:"password",props:{placeholder:"Please Enter Password",type:"password",eye:{open:!1}},rules:{required:!0},messages:{required:"Password Cannot Be Empty!!"}}]}},methods:{submitHandler:function(e,t){var s=this;e.preventDefault();var a={};a.email=t.email,a.password=t.password,Object(o["d"])(a).then((function(e){if(0===e.code)localStorage.setItem("token",e.data),s.$store.dispatch("set_Token",e.data),s.$router.push({path:"/personal"});else{var t=s.$createToast({txt:"Login Fail",type:"error",time:1500});t.show()}}))},goIndex:function(){this.$router.push({path:"/index"})}}},n=i,l=(s("f8f9"),s("4ac2")),d=Object(l["a"])(n,a,r,!1,null,"1ce024be",null);t["default"]=d.exports},f8f9:function(e,t,s){"use strict";s("57e3")}}]);
//# sourceMappingURL=chunk-50b66b99.eb106081.js.map