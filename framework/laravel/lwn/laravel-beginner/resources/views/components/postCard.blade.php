@props(['post', 'full' => false])

<div class="card flex gap-6">
    {{--Cover Photo--}}
    <div class="w-24 h-24 shrink-0 rounded-lg overflow-hidden">
        @if($post->image)
            <img
                src="{{asset('storage/' . $post->image)}}"
                alt=""
                class="img-cover"
            >
        @else
            <img
                src="{{asset('storage/posts_images/default.png')}}"
                alt=""
                class="img-cover"
            >
        @endif
    </div>

    {{--Content--}}
    <div class="flex-1 min-w-0">
        {{--Title--}}
        <h2 class="title4">
            {{$post->title}}
        </h2>

        {{--Author and Date--}}
        <div class="text-xs font-light mb-4">
            <span>
                Posted {{$post->created_at->diffForHumans()}} by
            </span>
            <a href="{{route('posts.user', $post->user)}}"
               class="text-blue-500 font-medium">
                {{ $post->user->username }}
            </a>
        </div>

        {{--Body--}}
        @if($full)
            <div class="text-sm">
                <span>{{$post->body}}</span>
            </div>
        @else
            <div class="text-sm leading-5">
                <span>{{Str::words($post->body, 10)}}</span>
                <a
                    href="{{route('posts.show', $post)}}"
                    class="text-blue-500 ml-2 whitespace-nowrap"
                >Read more &rarr;</a>
            </div>
        @endif

        <div class="flex items-center justify-end gap-4 mt-6">
            {{$slot}}
        </div>
    </div>

</div>
