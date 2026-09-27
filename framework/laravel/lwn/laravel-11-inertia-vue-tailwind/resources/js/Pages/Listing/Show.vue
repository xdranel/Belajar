<script setup>


import Container from "@/Components/Container.vue";
import {Link, router} from "@inertiajs/vue3";
import {ref} from "vue";
import Modal from "@/Components/Modal.vue";
import PrimaryBtn from "@/Components/PrimaryBtn.vue";

const props = defineProps({
    listing: Object,
    user: Object,
    canModify: Boolean,
})

const showConfirmModal = ref(false);

const closeModal = () => {
    showConfirmModal.value = false;
}

const deleteListing = () => {
    router.delete(route('listing.destroy', props.listing.id))
}

const toggleApprove = () => {
    let msg = props.listing.approved
        ? "Disapprove this listing?"
        : "Approve this listing?";

    if (confirm(msg)) {
        router.put(route('admin.approve', props.listing.id))
    }
}
</script>

<template>
    <Head title="Listing Details"></Head>

    <!-- Admin -->
    <div v-if="$page.props.auth.user.role === 'admin'"
         class="bg-slate-800 text-white mb-6 p-6 rounded-md font-medium flex items-center justify-between"
    >
        <p>
            This listing is {{listing.approved ? 'Approved' : 'Disapproved'}}
        </p>
        <button @click.prevent="toggleApprove"
            class="bg-slate-600 px-3 py-1 rounded-md">
            {{ listing.approved ? 'Disapprove it' : 'Approve it' }}
        </button>
    </div>

    <Container class="flex gap-4">
        <div class="w-1/4 rounded-md overflow-hidden">
            <img :src="listing.image
            ? `/storage/${listing.image}`
            : '/storage/images/listing/default.png'"
                 alt=""
                 class="w-full h-full object-cover object-center"
            >
        </div>

        <div class="w-3/4">
            <!-- Listing Details -->
            <div class="mb-6">
                <div class="flex items-end justify-between mb-2">
                    <p class="text-slate-500 w-full border-b">Listing Detail</p>

                    <!-- Edit and Delete Buttons -->
                    <div v-if="canModify" class="pl-4 flex items-center gap-4">
                        <Link
                            :href="route('listing.edit', listing.id)"
                            class="bg-green-500 rounded-md text-white px-6 py-2 hover:outline outline-green-500 outline-offset-2"
                        >Edit
                        </Link>

                        <button @click="showConfirmModal = true"
                                type="button"
                                class="bg-red-500 rounded-md text-white px-6 py-2 hover:outline outline-red-500 outline-offset-2"
                        >
                            Delete
                        </button>
                    </div>
                </div>

                <h3 class="font-bold text-2xl mb-4">{{ listing.title }}</h3>
                <p class="wrap-break-word">{{ listing.desc }}</p>
            </div>

            <!-- Contact Details -->
            <div class="mb-6">
                <p class="text-slate-500 w-full border-b mb-2">Contact Detail</p>

                <!-- Email -->
                <div v-if="listing.email" class="flex items-center gap-2 mb-2">
                    <i class="fa-solid fa-at"></i>
                    <p>Email:</p>
                    <a :href="`mailto:${listing.email}`" class="text-link">
                        {{ listing.email }}
                    </a>
                </div>

                <!-- Link -->
                <div v-if="listing.link" class="flex items-center gap-2 mb-2">
                    <i class="fa-solid fa-up-right-from-square"></i>
                    <p>External Link:</p>
                    <a :href="listing.link" target="_blank" class="text-link">
                        {{ listing.link }}
                    </a>
                </div>

                <!-- User -->
                <div class="flex items-center gap-2 mb-2">
                    <i class="fa-solid fa-user"></i>
                    <p>Listed By:</p>
                    <Link
                        :href="route('home', {user_id: user.id})"
                        class="text-link"
                    >{{ user.name }}
                    </Link>
                </div>
            </div>

            <!-- Tags -->
            <div v-if="listing.tags" class="mb-6">
                <p class="text-slate-500 w-full border-b mb-2">Tags</p>

                <div class="flex items-center gap-3">
                    <div v-for="tag in listing.tags.split(',')"
                         :key="tag"
                    >
                        <Link
                            :href="route('home', {tag})"
                            class="bg-slate-500 text-white px-2 py-px rounded-full hover:bg-slate-700 dark:hover:bg-slate-900">
                            {{ tag }}
                        </Link>
                    </div>
                </div>
            </div>
        </div>

        <Modal :show="showConfirmModal" @close="closeModal">
            <div class="space-y-4 text-left mb-4">
                <h3 class="text-lg font-medium text-gray-900 dark:text-white">
                    Are you sure you want to delete this listing?
                </h3>
            </div>

            <div class="flex items-center gap-3 pt-2 ">
                <PrimaryBtn @click="closeModal">Cancel</PrimaryBtn>
                <PrimaryBtn @click="deleteListing">Delete Listing</PrimaryBtn>
            </div>
        </Modal>
    </Container>
</template>
