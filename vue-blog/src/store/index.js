import Vue from 'vue'
import Vuex from 'vuex'

import d2admin from './modules/d2admin'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    PhotoId: 0,
    search: ''
  },
  mutations: {
    setPhotoId (state, param) {
      state.PhotoId = param
    },
    setSearch (state, msg) {
      state.search = msg
    }
  },
  modules: {
    d2admin
  }
})
