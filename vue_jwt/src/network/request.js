import axios from 'axios'

export function request(config) {
  const instance = axios.create({
    baseURL: 'http://localhost:8081/'
  })

  // 请求拦截器: 每次请求接口前需要进行拦截
  instance.interceptors.request.use(config => {
    // 每次请求需要将token信息放入请求头
    config.headers['token'] = localStorage.getItem('token')
    return config
  }, error => {
    // 在请求错误后要做些什么
    console.log(error)
  })

  // 响应拦截器: 请求接口并得到响应后进行拦截
  instance.interceptors.response.use(response => {
    // 在响应成功后必须要将响应的得到的数据返回,否则在main.js中无法拿到响应成功的数据
    return response.data
  }, error => {
    // 在响应错误后要做些什么
    console.log(error)
  })

  // 直接返回一个promise
  return instance(config)
}
