<script setup>

import {ref} from "vue";

const emit = defineEmits([
    'image'
])

const props = defineProps({
    listingImage: String,
})
const currentImage = props.listingImage
    ? `/storage/${props.listingImage}`
    : null;

const preview = ref(currentImage);
const oversizedImage = ref(false);
const showRevertBtn = ref(false);

const imageSelected = (e) => {
    preview.value = URL.createObjectURL(e.target.files[0]);
    oversizedImage.value = e.target.files[0].size > 3000000;
    showRevertBtn.value = true;
    emit('image', e.target.files[0]);
}

const revertImageChange = () => {
    preview.value = currentImage;
    showRevertBtn.value = false;
    oversizedImage.value = false;
    emit('image', null);
}
</script>

<template>
    <div>
        <span class="block text-sm font-medium text-slate-700 dark:text-slate-300"
              :class="{'text-red-500!' : oversizedImage}"
        >{{ oversizedImage ? 'Image size must be less than 3MB' : 'Image (Max size 3MB)' }}</span>

        <label for="image"
               class="block rounded-md mt-1 bg-slate-300 h-40 overflow-hidden cursor-pointer border-slate-300 border relative"
               :class="{'border-red-500!' : oversizedImage}"
        >

            <img :src="preview ?? '/storage/images/listing/default.png'"
                 class="object-center object-cover w-full h-full"
                 alt="">
            <button
                class="absolute top-2 right-2 bg-white/75 w-8 h-8 rounded-full grid place-items-center text-slate-700"
                v-if="showRevertBtn"
                @click.prevent="revertImageChange"
                type="button">

                <i class="fa-solid fa-rotate-left"></i>
            </button>
        </label>

        <input @input="imageSelected" type="file" id="image" name="image" hidden="">
    </div>
</template>
