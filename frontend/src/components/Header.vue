<template>
    <div>
        <b-navbar toggleable="lg" type="dark" variant="dark">
            <b-container>
                <b-navbar-brand href="#">Air</b-navbar-brand>

                <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

                <b-collapse id="nav-collapse" is-nav>
                    <b-navbar-nav>
                        <b-nav-item href="#" active>首页</b-nav-item>
                        <b-nav-item href="#">航班查询</b-nav-item>
                        <b-nav-item href="#">会员专区</b-nav-item>
                        <b-nav-item href="#">订单管理</b-nav-item>
                    </b-navbar-nav>

                    <!-- Right aligned nav items -->
                    <b-navbar-nav class="ml-auto">
                        <b-avatar size="2rem" style="margin:auto; "></b-avatar>
                        <b-nav-item-dropdown right>
                            <!-- Using 'button-content' slot -->
                            <template v-slot:button-content>
                                <em>{{username}}</em>
                            </template>
                            <b-dropdown-item href="#">个人资料</b-dropdown-item>
                            <b-dropdown-item @click="logout">Sign Out</b-dropdown-item>
                        </b-nav-item-dropdown>
                    </b-navbar-nav>
                </b-collapse>
            </b-container>
        </b-navbar>
    </div>
</template>

<script>
    export default {
        name: "Header",
        data() {
            return {
                username: 'username'
            }
        },
        methods: {
            logout() {
                window.sessionStorage.removeItem('token')
                this.$router.push('/login')
            }
        }
    }
</script>

<style lang="less" scoped>
</style>
