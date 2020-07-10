<template>
    <div class="register_container">
        <div class="register_box">
            <div class="avatar_box">
                <img src="../assets/logo.png" alt="logo">
            </div>

            <el-form class="register_form" :model="registerForm" :rules="registerFormRules" ref="registerFormRef">

                <el-form-item prop="username">
                    <el-input prefix-icon="el-icon-user" v-model="registerForm.username" placeholder="用户名"></el-input>
                </el-form-item>

                <el-form-item prop="password">
                    <el-input prefix-icon="el-icon-lock" v-model="registerForm.password" type="password"
                              placeholder="输入密码"></el-input>
                </el-form-item>

                <el-form-item prop="password_x">
                    <el-input prefix-icon="el-icon-lock" v-model="registerForm.password_x" type="password"
                              placeholder="确认密码"></el-input>
                </el-form-item>

                <el-form-item class="btns">
                    <el-button type="info" plain @click="login_x">登录</el-button>
                    <el-button type="primary" @click="register_x">注册</el-button>
                </el-form-item>

            </el-form>

        </div>

    </div>
</template>

<script>
    export default {
        name: "Register",
        data() {

            const validPass_x = (rule, value, callback) => {
                if (value !== this.registerForm.password) {
                    callback(new Error('两次输入密码不一致!'))
                } else
                    callback()
            };

            return {
                registerForm: {
                    username: '15996618001',
                    password: '123456',
                    password_x: '123456'
                },
                registerFormRules: {
                    username: [
                        {required: true, message: '请输入用户名', trigger: 'blur'},
                        {min: 3, max: 15, message: "长度在 3 到 15 个字符", trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入登录密码', trigger: 'blur'},
                        {min: 6, max: 15, message: "长度在 6 到 15 个字符", trigger: 'blur'},
                    ],
                    password_x: [
                        {validator: validPass_x, trigger: 'blur'}
                    ],
                }
            }
        },
        methods: {
            login_x() {
                this.$router.push('/login');
            },
            register_x() {
                this.$refs.registerFormRef.validate(async valid => {
                    if (!valid) return;

                    //连接后台go服务器部分
                    var params = new URLSearchParams();
                    params.append('telephone', this.registerForm.username)
                    params.append('password', this.registerForm.password)
                    var status = 400
                    await this.$axios.post('http://localhost:8080/signup', params).then(
                        function (response) {
                            console.log(response);
                            status = response.status
                        }
                    ).catch(
                        function (error) {
                            console.log(error.response.status)
                            status = error.response.status
                        }
                    )
                    if (status !== 200) {
                        return this.$message.error("注册失败！");
                    }
                    this.$message({
                        message: "注册成功",
                        type: 'success'
                    })
                    await this.$router.push('/login');
                })
            }
        }
    }

</script>

<style lang="less" scoped>
    .register_form {
        position: absolute;
        bottom: 0;
        width: 100%;
        padding: 0 20px;
        box-sizing: border-box;
    }

    .register_container {
        background-image: url(../../public/imgs/plane-bg.jpg);
        background-size: cover;
        background-position: center center;
        height: 100%;
    }

    .btns {
        display: flex;
        justify-content: flex-end;
    }

    .register_box {
        width: 450px;
        height: 350px;
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
