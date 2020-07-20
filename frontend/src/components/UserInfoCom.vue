<template>
    <div>
        <b-container class="small-margin" style="margin-bottom: 5em">
            <b-card no-body>
                <b-tabs card>
                    <b-tab title="Tab 1">
                    </b-tab>
                    <b-tab title="Tab 2" active>
                        <b-form @submit="onSubmit" @reset="onReset" v-if="show">
                            <b-row align-h="start">
                                <b-col cols="6" style="margin: auto">
                                    <p style="font-size: 1.2em">
                                        用户姓名：{{form.username}}
                                    </p>
                                </b-col>
                                <b-col cols="6">
                                    <b-form-group
                                            id="input-group-1"
                                            label-for="input-1"
                                    >
                                        <b-form-input
                                                id="input-1"
                                                v-model="form.username"
                                                type="email"
                                                required
                                                placeholder="输入用户名"
                                        ></b-form-input>
                                    </b-form-group>
                                </b-col>
                            </b-row>

                            <b-form-group id="input-group-2" label="Your Name:" label-for="input-2">
                                <b-form-input
                                        id="input-2"
                                        v-model="form.name"
                                        required
                                        placeholder="Enter name"
                                ></b-form-input>
                            </b-form-group>

                            <b-form-group id="input-group-3" label="Food:" label-for="input-3">
                                <b-form-select
                                        id="input-3"
                                        v-model="form.food"
                                        :options="foods"
                                        required
                                ></b-form-select>
                            </b-form-group>

                            <b-form-group id="input-group-4">
                                <b-form-checkbox-group v-model="form.checked" id="checkboxes-4">
                                    <b-form-checkbox value="me">Check me out</b-form-checkbox>
                                    <b-form-checkbox value="that">Check that out</b-form-checkbox>
                                </b-form-checkbox-group>
                            </b-form-group>
                            <b-button-group>
                                <b-button type="reset" variant="dark">复位</b-button>
                                <b-button type="submit" variant="primary">提交</b-button>
                            </b-button-group>
                        </b-form>
                    </b-tab>
                </b-tabs>
            </b-card>
        </b-container>
    </div>
</template>

<script>
    export default {
        name: "UserInfoCom",
        data() {
            return {
                form: {
                    username: '',
                    name: '',
                    food: null,
                    checked: []
                },
                foods: [{text: 'Select One', value: null}, 'Carrots', 'Beans', 'Tomatoes', 'Corn'],
                show: true
            }
        },
        methods: {
            onSubmit(evt) {
                evt.preventDefault()
                console.log(JSON.stringify(this.form))
            },
            onReset(evt) {
                evt.preventDefault()
                // Reset our form values
                this.form.email = ''
                this.form.name = ''
                this.form.food = null
                this.form.checked = []
                // Trick to reset/clear native browser form validation state
                this.show = false
                this.$nextTick(() => {
                    this.show = true
                })
            }
        }
    }
</script>

<style scoped>

</style>
