<script setup>
import UpdateInfo from "@/Pages/Profile/Sections/UpdateInfo.vue";
import UpdatePassword from "@/Pages/Profile/Sections/UpdatePassword.vue";
import DeleteAccount from "@/Pages/Profile/Sections/DeleteAccount.vue";

defineProps({
    user: Object,
    status: String,
})
</script>

<template>
    <Head title="Profile"></Head>
    <UpdateInfo :user="user" :status="status"></UpdateInfo>
    <UpdatePassword></UpdatePassword>
    <DeleteAccount></DeleteAccount>
</template>
