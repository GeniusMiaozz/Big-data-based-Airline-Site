<template>
    <div>
        <div class="title">热门推荐</div>
        <p @click="menu1">笔记本</p>
        <p @click="menu2">手机</p>
    </div>
</template>

<script>
    import Msg from "./msg.js";

    export default {
        name: "Left",
        methods: {
            menu1: function () {
                Msg.$emit("val", "1")
            },
            menu2: function () {
                Msg.$emit("val", "2")
            }
        }
    }
</script>

<style scoped>
    .title {
        width: 100px;
        color: red;
    }

    p {
        list-style: none;
        height: 50px;
        background-color: white;
        line-height: 50px;
        cursor: pointer;
    }
</style>
