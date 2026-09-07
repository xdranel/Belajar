<x-layout>

    <a href="{{route('dashboard')}}" class="block mb-2 text-lg text-blue-500">
        &larr; Go back to your dashboard
    </a>
    <x-postCard :post="$post" full="{{true}}"/>

</x-layout>
