<script setup>
defineProps({
    show: {
        type: Boolean,
        default: false
    }
});

const emit = defineEmits(['close']);
</script>

<template>
    <teleport to="body">
        <div v-if="show" class="fixed inset-0 z-50 overflow-y-auto">
            <div
                class="fixed inset-0 bg-black opacity-50 transition-opacity"
                @click="emit('close')"
            ></div>

            <!-- Modal Dialog -->
            <div class="flex min-h-screen items-center justify-center p-4 text-center">
                <div class="relative transform overflow-hidden rounded-lg bg-white dark:bg-slate-800 dark:text-white text-left shadow-xl transition-all sm:my-8 sm:w-full sm:max-w-lg p-6">
                    <slot />
                </div>
            </div>
        </div>
    </teleport>
</template>
