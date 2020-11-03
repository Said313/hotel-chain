(this.webpackJsonpclient=this.webpackJsonpclient||[]).push([[0],{35:function(e,t,a){e.exports=a(65)},40:function(e,t,a){},64:function(e,t,a){},65:function(e,t,a){"use strict";a.r(t);var n=a(0),r=a.n(n),l=a(29),i=a.n(l),o=(a(40),a(30)),s=a(31),c=a(34),u=a(33),m=a(9),d=a(1),p=a(10),g=a.n(p),h=function(e){var t=e.state,a=e.logOut,n=Object(d.f)();return r.a.createElement("div",null,r.a.createElement("nav",{className:"navigation"},r.a.createElement("ul",null,r.a.createElement("li",null,r.a.createElement(m.b,{to:"/"},"Home")),r.a.createElement("li",null,r.a.createElement(m.b,{to:"/booking"},"Booking")),r.a.createElement("li",null,r.a.createElement(m.b,{to:"/profile"},"Profile"))),t.isLogged?r.a.createElement("div",null,r.a.createElement("p",null,t.user.firstname),r.a.createElement("button",{onClick:function(){a(),n.push("/")}},"Log out")):r.a.createElement("div",null,r.a.createElement("button",{className:"navButton",onClick:function(){n.push("/login")}},"Log in"),r.a.createElement("button",{className:"navButton",onClick:function(){n.push("/signup")}},"Sign up"))))},E=function(e){var t=e.state;return r.a.createElement("main",{className:"Home"},r.a.createElement("h2",null,"This is home page for ",t.user.type,"!"),r.a.createElement("p",null,t.isLogged?"You are logged in.":"Please, log in."))},f=a(15),v="http://localhost:8080/backend_war_exploded",b=function(e){var t=e.handleLoginSubmit,a=Object(d.f)(),n=Object(f.a)(),l=n.register,i=(n.errors,n.handleSubmit);return r.a.createElement("div",{className:"Login"},r.a.createElement("div",null,r.a.createElement("h3",null,"Log in"),r.a.createElement("form",{onSubmit:i((function(e){!function(e){g.a.post("".concat(v,"/services/auth/login"),{login:e.logLogin,password:e.logPassword}).then((function(e){t({user:e.data,isLogged:!0})})).catch((function(e){t({user:{},isLogged:!1}),window.alert("Cannot access the server!"),console.log(e)}))}(e)}))},r.a.createElement("div",null,r.a.createElement("input",{type:"text",className:"loginInput",name:"logLogin",placeholder:"Enter login",required:!0,ref:l({required:!0})})),r.a.createElement("div",null,r.a.createElement("input",{type:"password",className:"loginInput",name:"logPassword",placeholder:"Enter password",required:!0,minLength:"6",ref:l({required:!0})})),r.a.createElement("button",{className:"loginButton",type:"submit"},"Log in"),r.a.createElement("p",{className:"toSignupText"},"Do not have an account?"),r.a.createElement("button",{className:"signupButton",onClick:function(){a.push("/signup")}},"Sign up"))))},y=a(8),w=function(){var e=Object(d.f)(),t=Object(f.a)({mode:"onChange"}),a=t.register,l=t.errors,i=t.watch,o=t.handleSubmit,s=Object(n.useState)(!0),c=Object(y.a)(s,2),u=c[0],m=c[1],p=function(e){return r.a.createElement("p",{className:"validError"},e)};return r.a.createElement("div",{className:"Signup"},r.a.createElement("h3",null,"Sign up"),r.a.createElement("form",{onSubmit:o((function(t){if(u){var a=document.getElementById("signLogin").value;g.a.post("".concat(v,"/services/auth/signup"),{firstname:t.firstname,lastname:t.lastname,password:t.password,login:a,id_type:t.idType,id_number:t.idNumber,address:t.address,mobile_phone:t.mobilePhone,home_phone:t.homePhone,category:t.category}).then((function(t){window.alert("You are signed up successfully!"),e.push("/login")})).catch((function(e){window.alert("Cannot access the server!"),console.log(e)}))}}))},r.a.createElement("div",null,r.a.createElement("input",{type:"text",className:"signInput",name:"firstname",placeholder:"Firstname",required:!0,ref:a({required:!0})})),l.firstname&&p("Firstname is required!"),r.a.createElement("div",null,r.a.createElement("input",{type:"text",className:"signInput",name:"lastname",placeholder:"Lastname",required:!0,ref:a({required:!0})})),l.lastname&&p("Lastname is required!"),r.a.createElement("div",null,r.a.createElement("input",{type:"password",className:"signInput",name:"password",placeholder:"Password",ref:a({minLength:6,validate:{letters:function(e){return/[a-zA-Z]/.test(e)},digits:function(e){return/[0-9]/.test(e)}}})})),l.password&&"letters"===l.password.type&&p("Password should contain at least 1 english letter!"),l.password&&"minLength"===l.password.type&&p("Password should contain at least 6 characters!"),l.password&&"digits"===l.password.type&&p("Password should contain at least 1 digit!"),r.a.createElement("div",null,r.a.createElement("input",{type:"password",className:"signInput",name:"repeatPassword",placeholder:"Re-type password",ref:a({validate:{confirmPassword:function(e){return e===i("password")}}})})),l.repeatPassword&&"confirmPassword"===l.repeatPassword.type&&p("Passwords do not match!"),r.a.createElement("div",null,r.a.createElement("input",{type:"text",className:"signInput",name:"signLogin",placeholder:"Login",required:!0,onChange:function(e){var t=e.target;g.a.post("".concat(v,"/services/auth/checkLogin"),{login:t.value}).then((function(e){m(e.data)})).catch((function(e){window.alert("Cannot access the server!"),console.log(e)}))}})),!u&&p("This login is not available!"),r.a.createElement("div",null,r.a.createElement("input",{type:"text",id:"signLogin",className:"signInput",name:"address",placeholder:"Address",required:!0,ref:a({required:!0})})),l.address&&p("Address is required!"),r.a.createElement("div",{className:"signIdType"},r.a.createElement("label",{htmlFor:"idType",id:"id_type"},"ID type: "),r.a.createElement("select",{name:"idType",required:!0,ref:a({required:!0})},r.a.createElement("option",{value:""}),r.a.createElement("option",{value:"us_passport"},"US passport"),r.a.createElement("option",{value:"driving_license"},"Driving license"))),l.idType&&p("Choose ID Type"),r.a.createElement("div",null,r.a.createElement("input",{type:"text",className:"signInput",name:"idNumber",placeholder:"ID number",required:!0,ref:a({required:!0})})),l.idNumber&&p("ID Number is required!"),r.a.createElement("div",null,r.a.createElement("input",{type:"tel",className:"signInput",name:"mobilePhone",placeholder:"Mobile phone",required:!0,ref:a({required:!0})})),l.mobilePhone&&p("Mobile phone is required!"),r.a.createElement("div",null,r.a.createElement("input",{type:"tel",className:"signInput",name:"homePhone",placeholder:"Home phone",required:!0,ref:a({required:!0})})),l.homePhone&&p("Home phone is required!"),r.a.createElement("div",{className:"signCategory"},r.a.createElement("label",{htmlFor:"category"},"Category: "),r.a.createElement("div",{id:"category"},r.a.createElement("input",{type:"radio",id:"cat_none",value:"",ref:a,name:"category"}),r.a.createElement("label",{htmlFor:"cat_none"},"None"),r.a.createElement("input",{type:"radio",id:"cat_military",value:"military",ref:a,name:"category"}),r.a.createElement("label",{htmlFor:"cat_military"},"Military"),r.a.createElement("input",{type:"radio",id:"cat_vip",value:"vip",ref:a,name:"category"}),r.a.createElement("label",{htmlFor:"cat_vip"},"Vip"))),r.a.createElement("div",null,r.a.createElement("button",{type:"submit",className:"signupButton"},"Submit"))))},N=function(){return r.a.createElement("div",{className:"Profile"},r.a.createElement("h3",null,"This is Profile page!"))},q=function(e){var t=e.setBookingQuery,a=Object(d.f)();return r.a.createElement("div",{className:"Booking"},r.a.createElement("h3",null,"Find Hotels"),r.a.createElement("form",{onSubmit:function(e){e.preventDefault();var n=e.target,r=n.querySelector(".dest").value,l=n.querySelector("#startDate").value,i=n.querySelector("#endDate").value;g.a.post("".concat(v,"/services/booking/query"),{destination:r,start:l,end:i}).then((function(e){t(e.data),a.push("/booking/hotels")})).catch((function(e){window.alert("Cannot access the server!"),console.log(e)}))}},r.a.createElement("input",{type:"text",className:"dest",placeholder:"Enter destination"}),r.a.createElement("label",null,"Arrival: "),r.a.createElement("input",{type:"date",className:"bookingDate",id:"startDate",required:!0}),r.a.createElement("label",null,"Departure: "),r.a.createElement("input",{type:"date",className:"bookingDate",id:"endDate"}),r.a.createElement("div",null,r.a.createElement("button",{type:"submit",className:"findHotelsButton"},"Find hotels"))))},L=function(e){var t=e.state.hotelsList;return r.a.createElement("div",{className:"QueryResults"},r.a.createElement("h3",null,"Hotels Available"),t.forEach((function(e){return r.a.createElement("p",null,e.name)})))},S=(a(64),function(e){Object(c.a)(a,e);var t=Object(u.a)(a);function a(){var e;return Object(o.a)(this,a),(e=t.call(this)).handleLoginSubmit=function(t){e.setState(t)},e.logOut=function(){e.setState({user:{},isLogged:!1})},e.setBookingQuery=function(t){e.setState({hotelsList:t})},e.state={isLogged:!1,user:{type:"user"},hotelsList:[]},e}return Object(s.a)(a,[{key:"render",value:function(){return r.a.createElement(m.a,{basename:"/backend_war_exploded"},r.a.createElement("div",{className:"App"},r.a.createElement(h,{state:this.state,logOut:this.logOut}),r.a.createElement(d.c,null,r.a.createElement(d.a,{exact:!0,path:"/"},r.a.createElement(E,{state:this.state,login:this.login,logOut:this.logout})),r.a.createElement(d.a,{exact:!0,path:"/booking"},r.a.createElement(q,{setBookingQuery:this.setBookingQuery})),r.a.createElement(d.a,{exact:!0,path:"/booking/hotels"},r.a.createElement(L,{state:this.state})),r.a.createElement(d.a,{exact:!0,path:"/profile"},r.a.createElement(N,null)),r.a.createElement(d.a,{exact:!0,path:"/login"},r.a.createElement(b,{handleLoginSubmit:this.handleLoginSubmit})),r.a.createElement(d.a,{exact:!0,path:"/signup"},r.a.createElement(w,null)))))}}]),a}(n.Component));i.a.render(r.a.createElement(r.a.StrictMode,null,r.a.createElement(S,null)),document.getElementById("root"))}},[[35,1,2]]]);
//# sourceMappingURL=main.d5f40d1d.chunk.js.map