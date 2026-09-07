<script setup>
import Container from "@/Components/Container.vue";
import Title from "@/Components/Title.vue";
import InputField from "@/Components/InputField.vue";
import PrimaryBtn from "@/Components/PrimaryBtn.vue";
import {useForm} from "@inertiajs/vue3";
import ErrorMessages from "@/Components/ErrorMessages.vue";
import {ref} from "vue";
import Modal from "@/Components/Modal.vue";

const showConfirmModal = ref(false);

const form = useForm({
    password: '',
})

const submit = () => {
    form.delete(route('profile.destroy'), {
        onSuccess: () => {
        },
        onError: () => form.reset(),
        preserveScroll: true,
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
            <Title>Delete Account</Title>
            <p>
                Once your account is deleted, all of its resources and data will be permanently deleted.
                This action cannot be undone. Please be certain
            </p>
        </div>

        <button @click="showConfirmModal = true"
                class="px-6 py-2 text-white bg-red-500 rounded-lg">
            <i class="fa-solid fa-triangle-exclamation mr-2"></i>
            Delete Account
        </button>

        <Modal :show="showConfirmModal" @close="closeModal">
            <div class="space-y-4 text-left">
                <h3 class="text-lg font-medium text-gray-900 dark:text-white">Are you sure you want to delete your account?</h3>
                <p class="text-sm text-gray-600 dark:text-gray-400 mt-1 mb-4">
                    Please enter your password to confirm you would like to permanently delete your account.
                </p>
            </div>
:disabled="form.processing
            <ErrorMessages :errors="form.errors"></ErrorMessages>

            <form @submit.prevent="submit" class="space-y-4">
                <div class="mb-4">
                    <InputField
                        label="Confirm Password"
                        icon="key"
                        class="w-full"
                        type="password"
                        v-model="form.password"
                    ></InputField>
                </div>

                <div class="flex justify-end items-center gap-3 pt-2">
                    <PrimaryBtn @click="closeModal">Cancel</PrimaryBtn>
                    <PrimaryBtn :disabled="form.processing">Confirm Password</PrimaryBtn>
                </div>
            </form>
        </Modal>
    </Container>
</template>
