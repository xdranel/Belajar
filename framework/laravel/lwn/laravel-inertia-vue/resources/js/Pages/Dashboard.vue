<script setup>
import {Link, router} from "@inertiajs/vue3";
import PaginationLinks from "./Components/PaginationLinks.vue";
import {ref, watch} from "vue";
import {debounce, throttle} from "lodash/function.js";

const props = defineProps({
    users: Object,
    searchTerm: String,
    can: Object,
})

const search = ref(props.searchTerm)

watch(
    search,
    debounce((q) => {
        router.get('/dashboard', { search: q }, { preserveState: true, replace: true })
    }, 500)
)

const getDate = (date) =>
    new Date(date).toLocaleDateString("id-ID", {
        day: "numeric",
        month: "long",
        year: "numeric",
    })

</script>

<template>
    <Head :title="` | ${$page.component}`"/>

    <div>
        <div class="flex justify-end items-center mb-4 pt-3">
            <div class="w-1/4">
                <input type="search" placeholder="Search" v-model="search">
            </div>
        </div>

        <table>
            <thead>
            <tr>
                <th>Avatar</th>
                <th>Name</th>
                <th>Email</th>
                <th>Registration Date</th>
                <th v-if="can.delete_user">Delete</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="user in users.data" :key="user.id">
                <td>
                    <img :src="user.avatar
                         ? 'storage/' + user.avatar
                         : 'storage/avatars/default.png'"
                         class="avatar"
                         alt="avatar"
                    />
                </td>
                <td>{{ user.name }}</td>
                <td>{{ user.email }}</td>
                <td>{{ getDate(user.created_at) }}</td>
                <td v-if="can.delete_user">
                    <button class="bg-red-600 w-6 h-6 rounded-full"></button>
                </td>
            </tr>
            </tbody>
        </table>

        <div>
            <PaginationLinks :paginator="users"/>
        </div>
    </div>

</template>
