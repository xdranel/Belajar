<script setup>
</script>
<template>
    <div>
        <header class="bg-amber-500 text-white">
            <nav class="flex items-center justify-between p-4 max-w-5xl, mx-autoo">
                <div class="space-x-6">
                    <a href="/">Home</a>
                    <a href="/about">About</a>
                </div>
            </nav>
        </header>

        <main>
            <slot />
        </main>
    </div>
</template>
