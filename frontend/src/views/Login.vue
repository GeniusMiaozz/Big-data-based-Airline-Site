<template>
    <div class="login_container">
        <div class="login_box">
            <!--头像区域-->
            <div class="avatar_box">
                <img src="../assets/logo.png" alt="logo">
            </div>
            <!--登录表单区域-->
            <el-form class="login_form" :model="loginForm" :rules="loginFormRules" ref="loginFormRef">
                <el-form-item prop="username">
                    <el-input prefix-icon="el-icon-user" v-model="loginForm.username" placeholder="用户名"></el-input>
                </el-form-item>

                <el-form-item prop="password">
                    <el-input prefix-icon="el-icon-lock" v-model="loginForm.password" type="password"
                              placeholder="密码"></el-input>
                </el-form-item>

                <el-form-item class="btns">
                    <el-button type="info" plain @click="register_x">注册</el-button>
                    <el-button type="primary" @click="login_x">登录</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Login",
        data() {
            return {
                loginForm: {
                    username: '15996618001',
                    password: '123456'
                },
                //表单的验证规则对象
                loginFormRules: {
                    username: [
                        {required: true, message: '请输入用户名', trigger: 'blur'},
                        {min: 3, max: 15, message: "长度在 3 到 15 个字符", trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入登录密码', trigger: 'blur'},
                        {min: 6, max: 15, message: "长度在 6 到 15 个字符", trigger: 'blur'}
                    ],
                }
            }
        },
        methods: {
            login_x() {
                this.$refs.loginFormRef.validate(async valid => {
                    if (!valid) return;

                    //连接后台go服务器部分
                    const url='http://localhost:8080/signin'
                    var params = new URLSearchParams();
                    params.append('telephone',this.loginForm.username)
                    params.append('password',this.loginForm.password)

                    var status=400;

                    await this.$axios.post(
                        url,params
                    ).then(
                        function (response) {
                            console.log(response);
                            status=response.status
                        }
                    ).catch(function (error) {
                            console.log(error.response.status)
                            status=error.response.status
                    });

                    // window.sessionStorage.setItem('token', res.token)
                    if (status !== 200) {
                        return this.$message.error("登录失败！")
                    }
                    this.$message({
                        message: "登录成功",
                        type: 'success'
                    })
                    await this.$router.push('/home');
                })
            },
            register_x() {
                this.$router.push('/register');
            }
        }
    }

</script>

<style lang="less" scoped>
    .login_form {
        position: absolute;
        bottom: 0;
        width: 100%;
        padding: 0 20px;
        box-sizing: border-box;
    }

    .btns {
        display: flex;
        justify-content: flex-end;
    }

    .login_container {
        background-image: url(../../public/imgs/plane-bg.jpg);
        background-size: cover;
        background-position: center center;
        height: 100%;
    }

    .login_box {
        width: 450px;
        height: 300px;
        background-color: #fff;
        border-radius: 3px;

        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);

        .avatar_box {
            height: 130px;
            width: 130px;
            border: 1px solid #eeeeee;
            border-radius: 50%;
            padding: 10px;
            box-shadow: 0 0 10px #dddddd;
            position: absolute;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: #ffffff;

            img {
                width: 100%;
                height: 100%;
                border-radius: 50%;
                background-color: #eeeeee;
            }
        }
    }
</style>
