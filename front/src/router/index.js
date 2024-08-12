import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Manager',
    component: () => import('../views/Manager.vue'),
    redirect: '/login',
    children: [
      { path: 'home', name: 'Home', component: () => import('../views/manager/Home.vue'), },
      { path: 'product', name: 'Product', component: () => import('../views/manager/Product.vue'), },
      { path: 'person', name: 'Person', component: () => import('../views/manager/Person.vue'), },
      { path: 'activity', name: 'Activity', component: () => import('../views/manager/Activity.vue'), },
      { path: 'customer', name: 'Customer', component: () => import('../views/manager/Customer.vue'), },
      { path: 'linkman', name: 'Linkman', component: () => import('../views/manager/Linkman.vue'), },
      { path: 'clue', name: 'Clue', component: () => import('../views/manager/Clue.vue'), },
      { path: 'business', name: 'Business', component: () => import('../views/manager/Business.vue'), },
    ]
  },
  {
    path: '/login',
    name:'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/register',
    name:'Register',
    component: () => import('../views/Register.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
