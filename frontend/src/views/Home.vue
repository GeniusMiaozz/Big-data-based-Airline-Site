<template>
    <div>
        <el-container>
            <el-header>Header <el-button type="info" @click="logout">logout</el-button></el-header>
            <el-main>Main</el-main>
            <el-footer>Footer</el-footer>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "Home",
        methods: {
            logout() {
                window.sessionStorage.removeItem('token')
                this.$router.push('/login')
            }
        }
    }
</script>

<style lang="less" scoped>
    .el-header, .el-footer {
        background-color: #B3C0D1;
        color: #333;
        text-align: center;
        line-height: 60px;
    }

    .el-main {
        background-color: #E9EEF3;
        color: #333;
        text-align: center;
        line-height: 160px;
        height: 100%;
    }

    body > .el-container {
        margin-bottom: 40px;
    }
</style>
