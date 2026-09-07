<script setup>

defineProps({
    listing: Object,
})
</script>

<template>
    <div class="bg-white dark:border rounded-lg shadow-lg overflow-hidden datk:bg-slate-800 h-full flex flex-col justify-between">
        <div>
            <!--Image-->
            <div class="w-full h-48 bg-red-300"></div>

            <!--Title & User-->
            <div class="p-4 dark:bg-slate-700 dark:text-slate-200">
                <h3 class="font-bold text-xl mb-2 ">
                    {{listing.title.substring(0,40)}}...
                </h3>
                <p>
                    Listed on {{new Date(listing.created_at).toLocaleDateString()}} by
                    <button class="text-indigo-500 hover:underline">
                        {{listing.user.name}}
                    </button>
                </p>
            </div>
        </div>
    </div>
</template>
