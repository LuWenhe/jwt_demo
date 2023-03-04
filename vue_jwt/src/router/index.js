import Vue from 'vue'
import Router from 'vue-router'
import Login from "../views/Login"
import User from "../views/User"

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/user',
      name: '/user',
      component: User
    }
  ],
  mode: "history"
})
