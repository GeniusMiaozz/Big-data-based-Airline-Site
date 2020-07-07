<template>
    <div>
        <div v-if="kk==1">
            <GoodsList :goodId="1"/>
        </div>
        <div v-if="kk==2">
            <GoodsList :goodId="2"/>
        </div>

    </div>
</template>

<script>
    import Msg from "./msg.js";
    import GoodsList from "./GoodsList";

    export default {
        name: "Right",
        data() {
            return {
                kk: 0
            }
        },
        mounted: function () {
            var _this = this
            Msg.$on('val', function (m) {
                _this.kk = m;
            })
        },
        components: {
            GoodsList
        }

    }

</script>

<style scoped>

</style>
