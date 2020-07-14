<template>

</template>

<script>
    import Msg from './msg'

    export default {
        name: "GenTickets",
        data() {
            return {
                ticket_info_normal:null,
                ticket_type_normal:0,
                tickets_user: [
                    {
                        name: 'xxx',
                        id_cart: '320xxxxxxxxxxxx',
                    }
                ]
            }
        },
        mounted: function () {
            const _this=this;
            Msg.$on('choose_ticket',function (m) {
                _this.ticket_info_normal=m.ticket_info
                _this.ticket_type_normal=m.ticket_type
            });
            Msg.$on('user_info',function (m) {
                _this.tickets_user=m
                console.log(m)
            })
        }
    }
</script>

<style scoped>

</style>
