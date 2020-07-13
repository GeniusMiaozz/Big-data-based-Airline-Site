<template>
    <div>
        <Header :index="index" :username="username" />
        <HomeMain/>
    </div>
</template>

<script>
    import Header from "../components/Header";
    import HomeMain from "../components/HomeMain";

    export default {
        name: "Home",
        components: {HomeMain, Header},
        data(){
            return{
                index:1,
                username:'username'
            }
        }
    }
</script>
