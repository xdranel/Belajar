<x-layout>

    <h1 class="title1">Request a password email</h1>

    {{--Session Message--}}
    @if(session('status'))
        <x-flashMsg msg="{{session('status')}}"/>
    @endif

    <div class="mx-auto max-w-screen-sm card">
        <form action="{{route('password.request')}}" method="post">
            @csrf
            <div class="mb-4">
                @error('failed')
                <p class="error">{{$message}}</p>
                @enderror
            </div>

            {{--Email--}}
            <div class="mb-4">
                <label for="email">Email</label>
                <input type="text"
                       name="email"
                       value="{{ old('email') }}"
                       class="input @error('email') ring-red-500 @enderror"/>
                @error('email')
                <p class="error">{{$message}}</p>
                @enderror
            </div>


            {{--Login Button--}}
            <button class="btn">Submit</button>
        </form>
    </div>

</x-layout>

