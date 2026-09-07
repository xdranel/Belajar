<html>
<body>

@error("name")
<p>{{$message}}</p>
@enderror

@error("password")
    {{ $message }}
@enderror

</body>
</html>
