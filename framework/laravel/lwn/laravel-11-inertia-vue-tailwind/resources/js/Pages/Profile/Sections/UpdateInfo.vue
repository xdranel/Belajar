<script setup>
import Container from "@/Components/Container.vue";
import Title from "@/Components/Title.vue";
import InputField from "@/Components/InputField.vue";
import PrimaryBtn from "@/Components/PrimaryBtn.vue";
import {router, useForm} from "@inertiajs/vue3";
import ErrorMessages from "@/Components/ErrorMessages.vue";
import SessionMessages from "@/Components/SessionMessages.vue";
import {ref} from "vue";
import Modal from "@/Components/Modal.vue";

const showConfirmModal = ref(false);

const props = defineProps({
    user: Object,
    status: String,
})

const form = useForm({
    name: props.user.name,
    email: props.user.email,
})

const resendEmail = (e) => {
    router.post(route('verification.send'),
        {},
        {
        onStart: () => e.target.disabled = true,
        onFinish: () => e.target.disabled = false,
    })
}

const submit = () => {
    form.patch(route('profile.info'), {
        preserveScroll: true
    })
}

const closeModal = () => {
    showConfirmModal.value = false;
    form.clearErrors();
    form.reset();
}
</script>

<template>
    <Container class="mb-6">
        <div class="mb-6">
            <Title>Update Information</Title>
            <p>
                Update your account's profile information and email address.
            </p>
        </div>

        <PrimaryBtn @click="showConfirmModal = true">Update Profile</PrimaryBtn>

        <Modal :show="showConfirmModal" @close="closeModal">
            <ErrorMessages :errors="form.errors"></ErrorMessages>
            <form @submit.prevent="submit" class="space-y-6">

                <InputField
                    label="Name"
                    icon="id-badge"
                    class="w-full"
                    v-model="form.name"
                ></InputField>

                <InputField
                    label="Email"
                    icon="at"
                    class="w-full"
                    v-model="form.email"
                ></InputField>

                <div v-if="user.email_verified_at === null">
                    <SessionMessages :status="status"></SessionMessages>
                    <p>Your email address unverified.
                        <button @click="resendEmail"
                                class="text-indigo-500 font-medium underline dark:text-indigo-400 disabled:text-slate-400 disabled:cursor-wait"
                        >Click here, to send the verification email</button>
                    </p>
                </div>

                <div class="flex justify-end items-center gap-3 pt-2">
                    <PrimaryBtn @click="closeModal">Cancel</PrimaryBtn>
                    <PrimaryBtn :disabled="form.processing">Save Profile</PrimaryBtn>
                </div>
            </form>
        </Modal>
    </Container>
</template>
