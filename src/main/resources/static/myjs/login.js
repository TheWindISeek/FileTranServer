new Vue({
    el: "#login_vue",
    data() {
        return {
            login_page_mode: 1,
            //登录之前填写的信息
            login: {
                User_Name: "",
                User_Password: "",
                Remember_Password: 0
            },
            //注册时填写的数值
            register: {
                User_Name: "",
                User_Password: "",
                Confirm_Password: ""
            },
            //登录后使用的信息
            user_info: {
                userId: 0,
                userName: "",
                userPassword: "",
                userFilelist: 1,
                userFavorites: 1
            }
        }
    },
    methods: {
        userLogin() {
            var _this = this;
            axios(
                {
                    method: "post",
                    url: "http://localhost:8080/FileTranServer/User/login",
                    data: _this.login
                }
            ).then(function (resp) {
                console.log(resp.data);
            });
        },
        userRegister() {
            var _this = this;
            axios(
                {
                    method: "post",
                    url: "http://localhost:8080/FileTranServer/User/register",
                    data: _this.register
                }
            ).then(function (resp) {
                console.log(resp.data);
            });
        }
    }
})