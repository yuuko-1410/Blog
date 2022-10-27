import axios from "axios";
import { Message } from "view-ui-plus";

axios.defaults.baseURL = "http://localhost:2333";
axios.defaults.timeout = 10000;
/**
 * get方法，对应get请求
 * @param {String} url [请求的url地址]
 * @param {Object} params [请求时携带的参数]
 */
export function get(url, params) {
  return new Promise((resolve, reject) => {
    axios
      .get(url, {
        params: params,
      })
      .then((res) => {
        resolve(res.data);
      })
      .catch((err) => {
        reject(err.data);
      });
  });
}
/**
 * post方法，对应post请求
 * @param {String} url [请求的url地址]
 * @param {Object} params [请求时携带的参数]
 */
export function post(url, params) {
  return new Promise((resolve, reject) => {
    axios
      .post(url, params)
      .then((res) => {
        resolve(res.data);
      })
      .catch((err) => {
        reject(err.data);
      });
  });
}

// 响应拦截器
axios.interceptors.response.use((response) => {
  if (response.data.code === 200) {
    return Promise.resolve(response);
  } else {
    Message.error(response.data.msg);
    return Promise.reject(response);
  }
});
//请求拦截器
axios.interceptors.request.use((config) => {
  config.headers = {
    "Access-Control-Allow-Origin": "*",
    "Content-Type": "application/json",
    satoken: localStorage.getItem("satoken"),
  };

  return config;
});
