import axios from 'axios'
import router from "@/router";

const request = axios.create({
    baseURL: "/api",
    timeout: 5000
})

const whiteUrls = ['/home','/activity','/database','/jbrowse']

request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    let userJson = sessionStorage.getItem("user")
    if (!whiteUrls.includes(config.url)) {
        if(!userJson) {

        } else {
            let user = JSON.parse(userJson);
            config.headers['token'] = user.token;
        }
    }
    return config
}, error => {
    return Promise.reject(error)
});

// response 拦截器
// 可以在接口响应后统一处理结果
request.interceptors.response.use(
    response => {
        let res = response.data;
        // 如果是返回的文件
        if (response.config.responseType === 'blob') {
            console.log("isfile")
            return res
        }
        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        // 验证token
        if (res.code === '401') {
            router.push("/")
        }
        return res;
    },
    error => {
        console.log('err' + error) // for debug
        return Promise.reject(error)
    }
)


export default request

