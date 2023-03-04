import {request} from "./request"

export function login(user) {
  return request({
    url: '/user/login',
    params: {
      username: user.username,
      password: user.password
    }
  })
}

export function register(username, password) {
  return request({
    url: '/user/register',
    params: {
      username: username,
      password: password
    }
  })
}

export function test() {
  return request({
    url: '/user/test'
  })
}
