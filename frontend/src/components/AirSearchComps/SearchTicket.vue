<template>
    <b-form @submit="onSubmit" @reset="onReset" v-if="show">
        <b-row align-h="start">
            <b-col cols="5">
                <b-form-group
                        id="input-group-1"
                        label="出发城市"
                        label-for="input-1"
                >
                    <b-form-input
                            id="input-1"
                            v-model="form.city_from"
                            required
                            placeholder=""
                    ></b-form-input>
                </b-form-group>
            </b-col>
            <b-col cols="2" style="text-align: center">
                <b-button variant="outline-primary" class="button_x" @click="swapFromTo">
                    <b-icon-arrow-repeat></b-icon-arrow-repeat>
                </b-button>
            </b-col>
            <b-col cols="5">
                <b-form-group
                        id="input-group-2"
                        label="到达城市"
                        label-for="input-2"
                >
                    <b-form-input
                            id="input-2"
                            v-model="form.city_to"
                            required
                            placeholder=""
                    ></b-form-input>
                </b-form-group>
            </b-col>
        </b-row>
        <b-row align-h="start">
            <b-col>
                <b-form-group
                        id="input-group-3"
                        label="出发日期"
                        label-for="input-3">
                    <b-form-datepicker v-model="form.date_start"
                                       class="mb-2"
                                       required
                    >
                    </b-form-datepicker>
                </b-form-group>
            </b-col>
        </b-row>
        <b-row align-h="start">
            <b-col cols="6">
                <b-form-group id="input-group-4" label="舱位:" label-for="input-4">
                    <b-form-select
                            id="input-4"
                            v-model="form.Position"
                            :options="Positions"
                            required
                    ></b-form-select>
                </b-form-group>
            </b-col>
            <b-col cols="6">
                <b-form-group id="input-group-5" label="乘机人数:" label-for="input5">
                    <b-form-select id="input5" v-model="form.num" :options="nums" required>
                    </b-form-select>
                </b-form-group>
            </b-col>
        </b-row>
        <b-row align-h="start">
            <b-col cols="6">
                <b-button type="reset" variant="dark">复位</b-button>
            </b-col>
            <b-col cols="6">
                <b-button type="submit" variant="primary">查询</b-button>
            </b-col>
        </b-row>
    </b-form>
</template>

<script>
    import Msg from "./msg.js"

    export default {
        name: "SearchTicket",
        data() {
            return {
                form: {
                    city_from: '',
                    city_to: '',
                    date_start: '',
                    Position: '经济舱',
                    num: 1
                },
                nums:[
                    1,2,3,4
                ],
                Positions: [
                    '经济舱', '头等舱', '商务舱'
                ],
                show: true,
            }
        },
        methods: {
            onSubmit(evt) {
                evt.preventDefault()
                console.log(JSON.stringify(this.form))
                Msg.$emit("val", this.form)
            },
            onReset(evt) {
                evt.preventDefault()
                // Reset our form values
                this.form.city_from = ''
                this.form.city_to = ''
                this.form.date_start = ''
                this.form.Position = null
                this.form.number = 1
                // Trick to reset/clear native browser form validation state
                this.show = false
                this.$nextTick(() => {
                    this.show = true
                })
            },
            swapFromTo() {
                [this.form.city_to, this.form.city_from] = [this.form.city_from, this.form.city_to]
            }
        },
    }
</script>

<style scoped>
    .button_x {
        border-radius: 50%;
        width: 45px;
        height: 45px;
        margin-top: 25px;
    }
</style>
