<script setup>

defineProps({
    user: String
})
</script>

<template>
    <Head :title="` | ${$page.component}`" />

    <div>
        <h1 class="text-3xl font-bold p-3.5">About {{ $page.props.auth.user }}</h1>
    </div>
</template>
