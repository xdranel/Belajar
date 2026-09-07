<script setup>

import {useForm, usePage} from "@inertiajs/vue3";
import TextInput from "../Components/TextInput.vue";
import {ref} from "vue";

const page = usePage();
const showSuccess = ref(!!page.props.flash.success)

if (showSuccess.value) {
    setTimeout(() => {
        showSuccess.value = false;
    }, 4000); // 4000ms = 4 seconds
}

const form = useForm({
    email: null,
    password: null,
    remember: null,
})

const submit = () => {
    form.post(route('login'), {
        preserveScroll: true,
        onError: () => form.reset("password", "remember"),
    })
}
</script>
<template>
    <!--    <Head title="Register" />-->
    <Head :title="` | ${$page.component.split('/').pop()}`"/>
    <p v-if="showSuccess"
       class="p-4 bg-green-200">{{$page.props.flash.success}}</p>

    <h1 class="title">Login to your account</h1>

    <div class="w-2/4 mx-auto">
        <form @submit.prevent="submit">

            <TextInput
                name="email"
                type="email"
                v-model="form.email"
                :message="form.errors.email"
            />

            <TextInput
                name="password"
                type="password"
                v-model="form.password"
                :message="form.errors.password"
            />

            <div class="flex items-center justify-between mb-2">
                <div class="flex items-center gap-2">
                    <label>Remember me</label>
                    <input type="checkbox" v-model="form.remember"/>
                </div>

                <p class="text-slate-600">Need an account?
                    <a :href="route('register')" class="text-link">Register</a>
                </p>
            </div>

            <div>
                <button class="primary-btn" :disabled="form.processing">Login</button>
            </div>
        </form>
    </div>
</template>
