$(document).ready(function () {
  $("#nav-align-btn").click(function () {
    $(".header-menu").css("justify-content", "end")
  })

  $("#dev-box-color-btn").click(function () {
    var color = $(".body").css("background-color")
    if (color == "red") {
      $(".card-content-2").css("background-color", "red")
    } else {
      $(".card-content-2").css("background-color", "cyan")
    }
  })
})
