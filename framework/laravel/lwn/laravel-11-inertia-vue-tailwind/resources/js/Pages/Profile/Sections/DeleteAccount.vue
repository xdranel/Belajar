<script setup>
import Container from "@/Components/Container.vue";
import Title from "@/Components/Title.vue";
import InputField from "@/Components/InputField.vue";
import PrimaryBtn from "@/Components/PrimaryBtn.vue";
import {useForm} from "@inertiajs/vue3";
import ErrorMessages from "@/Components/ErrorMessages.vue";
import {ref} from "vue";

const showConfirmPassword = ref(false);

const form = useForm({
    password: '',
})

const submit = () => {
    form.delete(route('profile.destroy'), {
        onSuccess: () => route('login'),
        onError: () => form.reset(),
        preserveScroll: true,
    })
}

const cancel = () => {
    showConfirmPassword.value = false;
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

        <ErrorMessages :errors="form.errors"></ErrorMessages>

        <div v-if="showConfirmPassword">
            <form @submit.prevent="submit" class="flex gap-4 items-end">

                <InputField
                    label="Confirm Password"
                    icon="key"
                    class="w-1/2"
                    type="password"
                    v-model="form.password"
                ></InputField>

                <p v-if="form.recentlySuccessful" class="text-green-500 font-medium">Saved!</p>

                <PrimaryBtn :disabled="form.processing">Confirm Password</PrimaryBtn>
                <button @click="cancel"
                        class="text-indigo-500 font-medium underline dark:text-indigo-400"
                >Cancel
                </button>
            </form>
        </div>

        <button v-if="!showConfirmPassword"
                @click="showConfirmPassword = true"
                class="px-6 py-2 text-white bg-red-500 rounded-lg">
            <i class="fa-solid fa-triangle-exclamation mr-2"></i>
            Delete Account
        </button>

    </Container>
</template>
