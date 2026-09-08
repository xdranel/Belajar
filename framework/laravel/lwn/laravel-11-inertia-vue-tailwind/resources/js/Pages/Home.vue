<script setup>

import Card from "@/Components/Card.vue";
import PaginationLinks from "@/Components/PaginationLinks.vue";
import InputField from "@/Components/InputField.vue";
import {router, useForm} from "@inertiajs/vue3";

const params = route().params;

const props = defineProps({
    listings:Object,
    searchTerm: String,
})

const form = useForm({
    search: props.searchTerm,
})

const search = () => {
    router.get(route('home'), {
        search: form.search,
        user_id: params.user_id,
        tag: params.tag,
    })
}
</script>

<template>
    <Head title="Latest Listing"></Head>

    <div class="flex items-center justify-between mb-4">
        <div class="flex items-center gap-2">
        </div>

        <div class="w-1/4">
            <form @submit.prevent="search">
                <InputField
                    type="search"
                    label=""
                    icon="magnifying-glass"
                    placeholder="Search..."
                    v-model="form.search"
                ></InputField>
            </form>
        </div>
    </div>

    <div v-if="Object.keys(listings.data).length">
        <div class="grid grid-cols-3 gap-4">
            <div v-for="listing in listings.data" :key="listing.id">
                <Card :listing="listing">

                </Card>
            </div>
        </div>
        <div class="mt-8">
            <PaginationLinks :paginator="listings">

            </PaginationLinks>

        </div>
    </div>
    <div v-else>
        No listings found
    </div>

</template>
