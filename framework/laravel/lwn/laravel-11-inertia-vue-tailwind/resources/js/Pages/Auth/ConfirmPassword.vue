<script setup>
import Container from "@/Components/Container.vue";
import Title from "@/Components/Title.vue";
import TextLink from "@/Components/TextLink.vue";
import InputField from "@/Components/InputField.vue";
import PrimaryBtn from "@/Components/PrimaryBtn.vue";
import {useForm} from "@inertiajs/vue3";
import ErrorMessages from "@/Components/ErrorMessages.vue";
import CheckBox from "@/Components/CheckBox.vue";
import SessionMessages from "@/Components/SessionMessages.vue";

const form = useForm({
    password: "",
})

const submit = () => {
    form.post(route('password.confirm'), {
        onFinish: () => form.reset()
    })
}
</script>

<template>
    <Head title="Password Confirmation"></Head>
    <Container class="w-1/2">
        <div class="mb-8 text-center">
            <p>
                This is a secure area of the application. Please confirm your password before continuing.
            </p>
        </div>

        <!-- Error Messages -->
        <ErrorMessages :errors="form.errors"></ErrorMessages>

        <form @submit.prevent="submit" class="space-y-6">

            <InputField label="Password"
                        type="password"
                        icon="key"
                        v-model="form.password"
            ></InputField>

            <PrimaryBtn :disabled="form.processing">Confirm</PrimaryBtn>

        </form>
    </Container>
</template>
