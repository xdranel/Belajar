<html>

<head>
    <title>Application Name - @yield('title')</title>
</head>

<body>

{{--@yield('header')--}}
@section('header')
    <h1>Default Header</h1>
@show

{{--@yield('content')--}}
@section('content')
    <p>Default Content</p>
@show

</body>

</html>
