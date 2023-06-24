new Vue({
    el: "#main_vue",
    data() {
        return {
            //管理员的信息
            adm: {
                admId: 2,
                admName: "admName",
                admPassword: "admPassword",
                admPermission: 7
            },
            keywords: [
                "关键字1",
                "关键字2"
            ],
            suffixs: [
                "suffix1",
                "suffix2"
            ],
            sizes: [
                "sizes1",
                "sizes2"
            ],
            usages: [
                "usages1",
                "usages2"
            ],
            index: 1,
            pageSize: 10,
            files: {
                fileId: 1,
                fileType: 2,
                fileName: "filename",
                fileCreator: 1,
                fileSize: 15,
                fileReferenceCount: 2,
                fileDownloadCount: 2,
                fileComments: 2,
                fileData: 1

                //并未补全
            },
            users: {
                userId: 1,
                userName: 2,
                userPassword: "123",
                userFilelist: 2,
                userFavorites: 1
            }
        }
    },

    methods: {
        admLogin() {
            var _this = this;
            axios(
                {
                    method: "post",
                    url: "http://localhost:8080/FileTranServer/Admin/login",
                    data: _this.adm
                }
            ).then(function (resp) {
                console.log(resp.data);
                _this.adm.admId = resp.data.admId;
                _this.adm.admPassword = resp.data.admPassword;
                _this.adm.admName = resp.data.admName;
            });
        },
        searchByKeyWords() {
            var _this = this;
            axios(
                {
                    method: "post",
                    url: "http://localhost:8080/FileTranServer/Search/byKeyWords",
                    data: _this.keywords
                }
            ).then(function (resp) {
                console.log(resp.data);
            });
        },
        searchByLabels() {
            var _this = this;
            var params = {
                'suffixs': _this.suffixs,
                'usages': _this.usages,
                'sizes': _this.sizes
            };
            console.log(params)

            axios(
                {
                    method: "post",
                    url: "http://localhost:8080/FileTranServer/Search/byLabels",
                    data: params
                }
            ).then(function (resp) {
                console.log(resp.data);
            });
        },
        searchAllFiles() {
            var _this = this;
            axios(
                {
                    method: "post",
                    url: "http://localhost:8080/FileTranServer/Search/allFiles",
                }
            ).then(function (resp) {
                console.log(resp.data);
            });
        },
        searchFileFromIndex() {
            var _this = this;
            axios(
                {
                    method: "post",
                    url: "http://localhost:8080/FileTranServer/Search/fileFromIndex",
                    data: {
                        'pageSize': _this.pageSize,
                        'index': _this.index
                    }
                }
            ).then(function (resp) {
                console.log(resp.data);
            });
        },
        uploadFile() {
            var _this = this;
            axios(
                {
                    method: "post",
                    url: "http://localhost:8080/FileTranServer/Upload/file",
                    data: _this.files
                }
            ).then(function (resp) {
                console.log(resp.data);
            });
        },
        uploadFiles() {
            //自行调用uploadfile完成
            console.log("自行调用upload file完成")
        },
        downloadFile() {
            var _this = this;
            axios(
                {
                    method: "post",
                    url: "http://localhost:8080/FileTranServer/Download/file",
                    data: _this.files
                }
            ).then(function (resp) {
                console.log(resp.data);
            });
        },
        downloadFiles() {
            console.log("自行调用download files完成")
        },
        searchUploadFiles() {
            var _this = this;
            axios(
                {
                    method: "post",
                    url: "http://localhost:8080/FileTranServer/Search/uploadFiles",
                    data: {
                        'users': _this.users,
                        'files': _this.files
                    }
                }
            ).then(function (resp) {
                console.log(resp.data);
            });
        },
        searchDownloadFiles() {
            var _this = this;
            axios(
                {
                    method: "post",
                    url: "http://localhost:8080/FileTranServer/Search/downloadFiles",
                    data: {
                        'users': _this.users,
                        'files': _this.files
                    }
                }
            ).then(function (resp) {
                console.log(resp.data);
            });
        },
        searchFavoriteFiles() {
            var _this = this;
            axios(
                {
                    method: "post",
                    url: "http://localhost:8080/FileTranServer/Search/favoriteFiles",
                    data: {
                        'users': _this.users,
                        'files': _this.files
                    }
                }
            ).then(function (resp) {
                console.log(resp.data);
            });
        },
        searchContentsFromFile() {
            var _this = this;
            axios(
                {
                    method: "post",
                    url: "http://localhost:8080/FileTranServer/Search/contentsFromFileteFiles",
                    data: _this.files
                }
            ).then(function (resp) {
                console.log(resp.data);
            });
        },
        deleteFileFromUser() {
            var _this = this;
            axios(
                {
                    method: "post",
                    url: "http://localhost:8080/FileTranServer/Delete/fileFromUser",
                    data: {
                        'files': _this.files,
                        'users': _this.users
                    }
                }
            ).then(function (resp) {
                console.log(resp.data);
            });
        },
        updateFile() {
            var _this = this;
            axios(
                {
                    method: "post",
                    url: "http://localhost:8080/FileTranServer/Update/file",
                    data: {
                        'files': _this.files,
                        'users': _this.users
                    }
                }
            ).then(function (resp) {
                console.log(resp.data);
            });
        },
        userLogin() {
            var _this = this;
            axios(
                {
                    method: "post",
                    url: "http://localhost:8080/FileTranServer/User/login",
                    data: _this.users
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
                    data: _this.users
                }
            ).then(function (resp) {
                console.log(resp.data);
            });
        }
    }
})