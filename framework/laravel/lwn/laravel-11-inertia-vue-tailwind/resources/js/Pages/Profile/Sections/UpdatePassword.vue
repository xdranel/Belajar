<script setup>
import Container from "@/Components/Container.vue";
import Title from "@/Components/Title.vue";
import InputField from "@/Components/InputField.vue";
import PrimaryBtn from "@/Components/PrimaryBtn.vue";
import {useForm} from "@inertiajs/vue3";
import ErrorMessages from "@/Components/ErrorMessages.vue";
import SessionMessages from "@/Components/SessionMessages.vue";
import {ref} from "vue";
import Modal from "@/Components/Modal.vue";

const showConfirmModal = ref(false);

const form = useForm({
    current_password: '',
    password: '',
    password_confirmation: '',
})

const submit = () => {
    form.put(route('profile.password'), {
        preserveScroll: true,
        onError: () => form.reset(),
        onSuccess: () => form.reset(),
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
            <Title>Update Password</Title>
            <p>
                Changing your password will require you to verify email to activate your new password
            </p>
        </div>

        <PrimaryBtn @click="showConfirmModal = true">Change Password</PrimaryBtn>


        <Modal :show="showConfirmModal" @close="closeModal">

            <div class="space-y-4 text-left">
                <h3 class="text-lg font-medium text-gray-900 dark:text-white">
                    Are you sure you want to change your password?
                </h3>
                <p class="text-sm text-gray-600 dark:text-gray-400 mt-1 mb-4">
                    Please enter your current password and new password.
                </p>
            </div>

            <ErrorMessages :errors="form.errors"></ErrorMessages>
            <form @submit.prevent="submit" class="space-y-6">

                <InputField
                    label="Current Password"
                    icon="key"
                    class="w-full"
                    type="password"
                    v-model="form.current_password"
                ></InputField>

                <InputField
                    label="New Password"
                    icon="key"
                    class="w-full"
                    type="password"
                    v-model="form.password"
                ></InputField>

                <InputField
                    label="Confirm New Password"
                    icon="key"
                    class="w-full"
                    type="password"
                    v-model="form.password_confirmation"
                ></InputField>

                <p v-if="form.recentlySuccessful" class="text-green-500 font-medium">Saved!</p>

                <div class="flex justify-end items-center gap-3 pt-2">
                    <PrimaryBtn @click="closeModal">Cancel</PrimaryBtn>
                    <PrimaryBtn :disabled="form.processing">Save Password</PrimaryBtn>
                </div>

            </form>
        </Modal>

    </Container>
</template>
