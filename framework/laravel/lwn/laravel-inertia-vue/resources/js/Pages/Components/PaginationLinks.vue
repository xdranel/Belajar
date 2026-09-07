<script setup>

defineProps({
    paginator: {
        type: Object,
        required: true,
    }
})

const makeLabel = (label) => {
    if (label.includes('Previous')) {
        return '&laquo'
    } else if (label.includes('Next')) {
        return '&raquo'
    } else {
        return label
    }
}

</script>
<template>
    <div v-if="paginator && paginator.links" class="flex justify-between items-start">
        <div class="flex items-center rounded-md overflow-hidden shadow-lg">
            <div v-for="link in paginator.links" :key="link.url">
                <component
                    :is="link.url ? 'Link' : 'span'"
                    :href="link.url"
                    v-html="makeLabel(link.label)"
                    class="border-x border-slate-50 w-12 h-12 grid place-items-center bg-white"
                    :class="{
                        'hover:bg-slate-100': link.url,
                        'text-zinc-400': !link.url,
                        'font-bold text-blue-500': link.active,
                    }"
                />
            </div>
        </div>

        <p>
            Showing {{ paginator.from }} to {{ paginator.to }} of {{ paginator.total }}
        </p>
    </div>
</template>

<!--<template>-->
<!--    <div v-if="paginator && paginator.links" class="flex justify-between items-start">-->
<!--        <div class="flex items-center rounded-md overflow-hidden shadow-lg">-->
<!--            <template v-for="(link, index) in paginator.links" :key="index">-->
<!--                <component-->
<!--                    :is="link.url ? 'Link' : 'span'"-->
<!--                    :href="link.url"-->
<!--                    class="border-x border-slate-50 w-12 h-12 grid place-items-center bg-white text-sm"-->
<!--                    :class="{-->
<!--                        'hover:bg-slate-100': link.url,-->
<!--                        'text-zinc-400': !link.url,-->
<!--                        'font-bold text-blue-500': link.active,-->
<!--                    }">-->
<!--                    <span v-html="makeLabel(link.label)"></span>-->
<!--                </component>-->
<!--            </template>-->
<!--        </div>-->
<!--    </div>-->
<!--</template>-->
