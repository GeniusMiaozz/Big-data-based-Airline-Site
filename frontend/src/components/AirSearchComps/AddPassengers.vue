<template>
    <b-form @submit="onSubmit" @reset="onReset" v-if="show">
        <b-row align-h="start">
            <b-col cols="6">
                <b-form-group
                    id="input-group-1"
                    label="乘机人身份证"
                    label-for="input1"
                >
                    <b-form-input
                            id="input-1"
                            v-model="form.id_card"
                            required
                            placeholder=""
                    >
                    </b-form-input>
                </b-form-group>
            </b-col>
            <b-col cols="6">
                <b-form-group
                    id="input-group-2"
                    label="乘机人姓名"
                    label-for="input2"
                >
                    <b-form-input
                        id="input-2"
                        v-model="form.name"
                        required
                        placeholder=""
                    >
                    </b-form-input>
                </b-form-group>
            </b-col>
        </b-row>
        <b-row align-h="start">
            <b-col cols="6">
                <b-button type="reset" variant="danger">复位</b-button>
            </b-col>
            <b-col cols="6">
                <b-button type="submit" variant="primary">添加</b-button>
            </b-col>
        </b-row>
    </b-form>
</template>

<script>
    export default {
        name: "AddPassengers",
        data() {
            return {
                form: {
                    id_card:'',
                    name: '',
                },

                show: true,
            }
        },
        methods: {
            onSubmit(evt) {
                evt.preventDefault()
                alert(JSON.stringify(this.form))
            },
            onReset(evt) {
                evt.preventDefault()
                // Reset our form values
                this.form.id_card=''
                this.form.name = ''
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
