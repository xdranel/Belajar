$(document).ready(function () {
  $("#submit-btn").click(function () {
    var minimum = parseInt($("#minimum").val())
    var maximum = parseInt($("#maximum").val())
    var result = 0
    for (let i = minimum; i <= maximum; i++) {
      if (i % 2 !== 0) {
        result += i
      }
    }
    console.log(result)
    $("#answer").text(result)
  })
})
