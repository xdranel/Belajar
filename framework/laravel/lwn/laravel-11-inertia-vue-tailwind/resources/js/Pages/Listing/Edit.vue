<script setup>

import {useForm} from "@inertiajs/vue3";
import Container from "@/Components/Container.vue";
import Title from "@/Components/Title.vue";
import InputField from "@/Components/InputField.vue";
import PrimaryBtn from "@/Components/PrimaryBtn.vue";
import TextArea from "@/Components/TextArea.vue";
import ImageUpload from "@/Components/ImageUpload.vue";
import ErrorMessages from "@/Components/ErrorMessages.vue";

const props = defineProps({
    listing: Object,
})

const form = useForm({
    title: props.listing.title,
    desc: props.listing.desc,
    tags: props.listing.tags,
    email: props.listing.email,
    link: props.listing.link,
    image: null,
    _method: 'PUT',
})

</script>

<template>
    <Head title="Edit Listing"></Head>

    <Container>
        <div class="mb-6">
            <Title>Edit Your Listing</Title>
        </div>
        <ErrorMessages :errors="form.errors"></ErrorMessages>

        <form @submit.prevent="form.post(route('listing.update', listing.id))"
            class="grid grid-cols-2 gap-6">
            <div class="space-y-6">
                <InputField label="Title"
                            icon="heading"
                            placeholder="My new listing"
                            v-model="form.title"
                ></InputField>

                <InputField label="Tags (comma separated)"
                            icon="tags"
                            placeholder="one, two, three"
                            v-model="form.tags"
                ></InputField>

                <TextArea label="Description"
                          icon="newspaper"
                          placeholder="This is my listing description"
                          v-model="form.desc"
                ></TextArea>
            </div>

            <div class="space-y-6">
                <InputField label="Email"
                            icon="at"
                            placeholder="example@email.com"
                            v-model="form.email"
                ></InputField>

                <InputField label="External Link"
                            icon="up-right-from-square"
                            placeholder="https://example.com"
                            v-model="form.link"
                ></InputField>

                <ImageUpload @image="(e) => (form.image = e)" :listingImage="listing.image">
                </ImageUpload>
            </div>

            <div>
                <PrimaryBtn :disabled="form.processing">Update</PrimaryBtn>
            </div>

        </form>
    </Container>

</template>
