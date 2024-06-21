import axios from 'axios'
import Configuration from '@/configuration'
// var backendHost = Configuration.value('backendHost')
// console.log('backendHost', backendHost)

// create an axios instance
const service = axios.create({
  baseURL: Configuration.value('backendHost'),
  // baseURL: 'http://localhost:8090/',
  timeout: 8000, // request time out
  headers: {
    // Authorization: 'Bearer ' + store.getters.token
    'Content-Type': 'application/json'
  }
})

export default service
