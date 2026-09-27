<script setup>
import {Head, Link, router} from "@inertiajs/vue3";
import Title from "@/Components/Title.vue";
import {ref} from "vue";
import PrimaryBtn from "@/Components/PrimaryBtn.vue";
import Modal from "@/Components/Modal.vue";
import PaginationLinks from "@/Components/PaginationLinks.vue";
import SessionMessages from "@/Components/SessionMessages.vue";

const props = defineProps({
    listings: Object,
    status: String,
})

const showConfirmModal = ref(false);
const selectedListing = ref(null);

const closeModal = () => {
    showConfirmModal.value = false;
    selectedListing.value = null;
}

const confirmDelete = (listing) => {
    selectedListing.value = listing;
    showConfirmModal.value = true;
}

const deleteListing = () => {
    if (!selectedListing.value) return;
    router.delete(route('listing.destroy', selectedListing.value.id), {
        onSuccess: () => {
            closeModal();
        }
    })
}
</script>

<template>
    <Head title="Dashboard"></Head>

    <SessionMessages :status="status"></SessionMessages>

    <div v-if="listings">
        <div v-if="Object.keys(listings.data).length">
            <div class="mb-6">
                <!-- Heading -->
                <div class="flex justify-between items-center mb-4">
                    <Title>Your latest listing</Title>

                    <div class="flex items-center gap-4 text-xs">
                        <p>Approved
                            <i class="fa-solid fa-circle-check text-green-500"></i>
                        </p>
                        <p>Pending Approval
                            <i class="fa-solid fa-circle-xmark text-red-500"></i>
                        </p>
                    </div>
                </div>

                <!-- Table -->
                <table
                    class="w-full table-fixed border-collapse overflow-hidden rounded-md text-sm ring-1 ring-slate-300 dark:ring-slate-600 bg-white dark:bg-slate-800 shadow-lg">
                    <thead class="bg-slate-300 text-xs uppercase text-slate-600 dark:text-slate-300 dark:bg-slate-900">
                    <tr>
                        <th class="w-3/4 p-3 text-left">Listing Title</th>
                        <th class="w-1/4 py-3 pr-3 text-right">View</th>
                        <th class="w-1/5 py-3 pr-3 text-right">Edit</th>
                        <th class="w-1/5 py-3 pr-3 text-right">Delete</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr v-for="listing in listings.data" :key="listing.id"
                        class="border-b border-slate-200 hover:bg-slate-100 dark:border-slate-800 dark:hover:bg-slate-600 dark:border-slate-600"
                    >
                        <td class="w-3/4 p-3 text-left">
                            <div class="flex items-center gap-2">
                                <img
                                    :src="listing.image ? `storage/${listing.image}` : 'storage/images/listing/default.png'"
                                    alt=""
                                    class="w-10 h-10 rounded-full object-cover object-center"
                                >
                                <h4 class="font-bold">
                                    {{ listing.title }}
                                    <i :class="`fa-solid fa-${listing.approved ? 'circle-check text-green-500' : 'circle-xmark text-red-500'}`"></i>
                                </h4>
                            </div>
                        </td>
                        <td class="w-1/4 py-3 pr-3 text-right text-indigo-600 dark:text-indigo-300">
                            <Link v-if="listing.approved"
                                  :href="route('listing.show', listing.id)"
                            >View
                            </Link>
                        </td>
                        <td class="w-1/5 py-3 pr-3 text-right text-indigo-600 dark:text-indigo-300">
                            <Link :href="route('listing.edit', listing.id)"
                            >Edit
                            </Link>
                        </td>
                        <td class="w-1/5 py-3 pr-3 text-right text-red-500">
                            <button type="button"
                                    @click="confirmDelete(listing)"
                            >Delete
                            </button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <PaginationLinks :paginator="listings"></PaginationLinks>
        </div>

        <div v-else>
            You have no listings yet.
        </div>
    </div>
    <div v-else>
        Due to violation of our terms and conditions, your account has been suspended. Please contact support for more
        information at <span class="text-link">email@admin.com</span>
    </div>

    <Modal :show="showConfirmModal" @close="closeModal">
        <div class="space-y-4 text-left mb-4">
            <h3 class="text-lg font-medium text-gray-900 dark:text-white">
                Are you sure you want to delete this listing?
            </h3>
        </div>

        <div class="flex items-center gap-3 pt-2">
            <PrimaryBtn @click="closeModal">Cancel</PrimaryBtn>
            <PrimaryBtn @click="deleteListing">Delete Listing</PrimaryBtn>
        </div>
    </Modal>
</template>
