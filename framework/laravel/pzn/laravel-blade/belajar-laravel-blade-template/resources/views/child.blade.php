@extends("parent")

@section("title", "Main Page")

@section("header")
    @parent
    <h1>Header Description</h1>
{{--    @parent--}}
@endsection

@section("content")
    <p>This is main page</p>
@endsection
