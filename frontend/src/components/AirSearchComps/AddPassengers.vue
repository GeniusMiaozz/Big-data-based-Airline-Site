<template>
    <b-form @submit="onSubmit" @reset="onReset" v-if="show">
        <b-row align-h="start" v-for="i in form.nums" :key="i">
            <b-col cols="6">
                <b-form-group
                        id="input-group-1"
                        :label="'乘机人身份证' + i "
                        label-for="input1"
                >
                    <b-form-input
                            id="input-1"
                            v-model="form.id_cards[i-1]"
                            required
                    >
                    </b-form-input>
                </b-form-group>
            </b-col>
            <b-col cols="6">
                <b-form-group
                        id="input-group-2"
                        :label="'乘机人姓名' + i "
                        label-for="input2"
                >
                    <b-form-input
                            id="input-2"
                            v-model="form.names[i-1]"
                            required
                            placeholder=""
                    >
                    </b-form-input>
                </b-form-group>
            </b-col>
        </b-row>
        <b-row>
            <b-col cols="6">
                <b-button-group>
                    <b-button @click="add" variant="outline-info">添加</b-button>
                    <b-button @click="delete_x" variant="outline-danger">删除</b-button>
                </b-button-group>
            </b-col>
            <b-col cols="6">
                <b-button-group>
                    <b-button type="reset" variant="dark">复位</b-button>
                    <b-button type="submit" variant="primary">提交</b-button>
                </b-button-group>
            </b-col>

        </b-row>
    </b-form>
</template>

<script>
    import Msg from './msg.js'

    export default {
        name: "AddPassengers",
        data() {
            return {
                form: {
                    id_cards: [''],
                    names: [''],
                    nums: 1,
                },

                show: true,
            }
        },
        methods: {
            add() {
                if (this.form.nums < 4) {
                    this.form.names.push('')
                    this.form.id_cards.push('')
                    this.form.nums++
                }
            },
            delete_x() {
                if (this.form.nums > 1) {
                    this.form.names.pop()
                    this.form.id_cards.pop()
                    this.form.nums--
                }

            },
            onSubmit(evt) {
                evt.preventDefault()
                // alert(JSON.stringify(this.form))
                Msg.$emit('user_info', this.form)
                this.$message({
                    message: "乘客添加成功",
                    type: 'success'
                })
            },
            onReset(evt) {
                evt.preventDefault()
                // Reset our form values
                this.form.id_card = []
                this.form.name = []
                for (let n = 0; n < m; n++) {
                    this.form.id_card[n] = ''
                    this.form.name[n] = ''
                }
                // Trick to reset/clear native browser form validation state
                this.show = false
                this.$nextTick(() => {
                    this.show = true
                })
            }
        },
        mounted: function () {
            const _this = this;
            Msg.$on('search', function (m) {
                _this.form.nums = m.num
                _this.nums = m.num
                _this.form.id_card = []
                _this.form.name = []
                for (let n = 0; n < m; n++) {
                    _this.form.id_card[n] = ''
                    _this.form.name[n] = ''
                }
            })
        }
    }
</script>

<style scoped>

</style>
