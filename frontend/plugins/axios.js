// axios 러닝 가이드 https://yamoo9.github.io/axios/guide/interceptors.html
// nuxt axios 예제 https://codesandbox.io/s/v67j242yzl?file=/nuxt.config.js
// 도우미 nuxt/axios https://axios.nuxtjs.org/helpers/
import dayjs from "dayjs";

export default function ({ $axios, redirect }) {
  $axios.proxyHeaders = false;
  $axios.credentials = false;

  $axios.interceptors.request.use(
    function (config){
      config.baseURL = process.browser ? '' : 'http://localhost:8087'
      console.log(config.baseURL);
      let url = config.url;

      const timeStamp = dayjs().format('YYYYMMDDHHmmssSSS');
      if (url.indexOf('?') > 0) {
        url += '&t=' + timeStamp;
      } else {
        url += '?t=' + timeStamp;
      }

      config.url = url;
      return config;
    }
  )

  $axios.onError(error => {
    if(error.response.status === 500) {
      console.log("error!");
      redirect("/");
    }
  })
}
